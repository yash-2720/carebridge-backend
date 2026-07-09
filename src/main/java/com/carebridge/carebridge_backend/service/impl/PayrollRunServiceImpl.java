package com.carebridge.carebridge_backend.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.request.PayrollRunRequestDTO;
import com.carebridge.carebridge_backend.dto.response.PayrollRunResponseDTO;
import com.carebridge.carebridge_backend.entity.DonationRequest;
import com.carebridge.carebridge_backend.entity.DonationTransaction;
import com.carebridge.carebridge_backend.entity.PayrollRun;
import com.carebridge.carebridge_backend.enums.DonationStatus;
import com.carebridge.carebridge_backend.enums.DonationType;
import com.carebridge.carebridge_backend.enums.PayrollRunStatus;
import com.carebridge.carebridge_backend.enums.TransactionStatus;
import com.carebridge.carebridge_backend.exception.BusinessValidationException;
import com.carebridge.carebridge_backend.repository.DonationRequestRepository;
import com.carebridge.carebridge_backend.repository.DonationTransactionRepository;
import com.carebridge.carebridge_backend.repository.PayrollRunRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.PayrollRunService;

import jakarta.transaction.Transactional;

@Service
public class PayrollRunServiceImpl implements PayrollRunService {

	private PayrollRunRepository payrollRunRepository;
	private SequenceGenerator sequenceGenerator;
	private DonationRequestRepository donationRequestRepository;
	private DonationTransactionRepository donationTransactionRepository;

	public PayrollRunServiceImpl(PayrollRunRepository payrollRunRepository, SequenceGenerator sequenceGenerator,
			DonationRequestRepository donationRequestRepository,
			DonationTransactionRepository donationTransactionRepository) {
		this.payrollRunRepository = payrollRunRepository;
		this.sequenceGenerator = sequenceGenerator;
		this.donationRequestRepository = donationRequestRepository;
		this.donationTransactionRepository = donationTransactionRepository;
	}

	@Transactional
	public PayrollRunResponseDTO runPayroll(PayrollRunRequestDTO request) {

		if (payrollRunRepository.existsByPayrollMonthAndPayrollYear(request.getPayrollMonth(),
				request.getPayrollYear())) {

			throw new BusinessValidationException("Payroll already exists for the selected month and year.");
		}

		PayrollRun payrollRun = new PayrollRun();

		payrollRun.setPayrollRunId(sequenceGenerator.generateId("PRL"));
		payrollRun.setPayrollMonth(request.getPayrollMonth());
		payrollRun.setPayrollYear(request.getPayrollYear());
		payrollRun.setRunStatus(PayrollRunStatus.IN_PROGRESS);
		payrollRun.setProcessedBy("SYSTEM");
		payrollRun.setRemarks(null);

		payrollRun = payrollRunRepository.save(payrollRun);

		List<DonationRequest> donationRequests = donationRequestRepository
				.findByDonationStatusAndIsActive(DonationStatus.INITIALIZED, true);

		if (donationRequests.isEmpty()) {
			throw new BusinessValidationException("No eligible donation requests found for payroll processing.");
		}

		YearMonth payrollPeriod = YearMonth.of(request.getPayrollYear(), request.getPayrollMonth());

		int processedRequests = 0;
		int skippedRequests = 0;
		BigDecimal totalDonationAmount = BigDecimal.ZERO;

		for (DonationRequest donationRequest : donationRequests) {

			YearMonth donationStartPeriod = YearMonth.from(donationRequest.getDonationStartDate());

			// Rule 1: Donation should have started
			if (donationStartPeriod.isAfter(payrollPeriod)) {
				skippedRequests++;
				continue;
			}

			// Rule 2: Additional validation only for recurring donations
			if (donationRequest.getDonationType() == DonationType.RECURRING) {

				YearMonth donationEndPeriod = donationRequest.getDonationEndDate() == null ? null
						: YearMonth.from(donationRequest.getDonationEndDate());

				if (donationEndPeriod != null && donationEndPeriod.isBefore(payrollPeriod)) {

					skippedRequests++;
					continue;
				}
			}
			// Rule 3:
			// If execution reaches here,
			// the donation is eligible for payroll processing.
			// Create DonationTransaction here

			DonationTransaction donationTransaction = new DonationTransaction();
			donationTransaction.setDonationRequest(donationRequest);
			donationTransaction.setPayrollPeriod(payrollPeriod.toString());
			donationTransaction.setDeductedAmount(donationRequest.getDonationAmount());
			donationTransaction.setPayrollRun(payrollRun);
			donationTransaction.setDonationTransactionId(sequenceGenerator.generateId("DTR"));
			donationTransaction.setRemarks("Payroll Processed");
			donationTransaction.setTransactionStatus(TransactionStatus.PROCESSED);
			donationTransaction.setProcessedOn(LocalDateTime.now());

			donationTransactionRepository.save(donationTransaction);
			processedRequests++;
			totalDonationAmount = totalDonationAmount.add(donationRequest.getDonationAmount());

			boolean updateStatus = false;

			if (donationRequest.getDonationType() == DonationType.ONE_TIME) {
				donationRequest.setDonationStatus(DonationStatus.PROCESSED);
				updateStatus = true;
			}

			if (donationRequest.getDonationType() == DonationType.RECURRING
					&& donationRequest.getDonationEndDate() != null) {
				YearMonth donationEndPeriod = YearMonth.from(donationRequest.getDonationEndDate());

				if (payrollPeriod.equals(donationEndPeriod)) {

					donationRequest.setDonationStatus(DonationStatus.PROCESSED);

					updateStatus = true;
				}

			}
			if (updateStatus) {
				donationRequestRepository.save(donationRequest);
			}

		}
		payrollRun.setRunStatus(PayrollRunStatus.COMPLETED);
		payrollRun.setProcessedOn(LocalDateTime.now());
		payrollRun = payrollRunRepository.save(payrollRun);

		PayrollRunResponseDTO response = new PayrollRunResponseDTO();
		response.setPayrollRunId(payrollRun.getPayrollRunId());
		response.setPayrollMonth(payrollRun.getPayrollMonth());
		response.setPayrollYear(payrollRun.getPayrollYear());
		response.setRunStatus(payrollRun.getRunStatus());
		response.setProcessedRequests(processedRequests);
		response.setSkippedRequests(skippedRequests);
		response.setTotalDonationAmount(totalDonationAmount);
		response.setProcessedOn(payrollRun.getProcessedOn());

		System.out.println("Skipped Requests :" + skippedRequests);
		System.out.println("Processed Requests :" + processedRequests);
		System.out.println("Total Donation Amount : " + totalDonationAmount);

		return response;
	}

}
