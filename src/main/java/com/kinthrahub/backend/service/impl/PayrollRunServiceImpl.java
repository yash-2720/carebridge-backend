package com.kinthrahub.backend.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.request.PayrollRunRequestDTO;
import com.kinthrahub.backend.dto.response.DonationTransactionResponseDTO;
import com.kinthrahub.backend.dto.response.PayrollRunResponseDTO;
import com.kinthrahub.backend.entity.DonationRequest;
import com.kinthrahub.backend.entity.DonationTransaction;
import com.kinthrahub.backend.entity.PayrollRun;
import com.kinthrahub.backend.enums.DonationStatus;
import com.kinthrahub.backend.enums.DonationType;
import com.kinthrahub.backend.enums.PayrollRunStatus;
import com.kinthrahub.backend.enums.TransactionStatus;
import com.kinthrahub.backend.exception.BusinessValidationException;
import com.kinthrahub.backend.exception.ResourceNotFoundException;
import com.kinthrahub.backend.repository.DonationRequestRepository;
import com.kinthrahub.backend.repository.DonationTransactionRepository;
import com.kinthrahub.backend.repository.PayrollRunRepository;
import com.kinthrahub.backend.sequence.SequenceGenerator;
import com.kinthrahub.backend.service.PayrollRunService;
import com.kinthrahub.backend.specification.DonationTransactionSpecification;
import com.kinthrahub.backend.specification.PayrollRunSpecification;

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

	@Override
	public Page<PayrollRunResponseDTO> getAllPayrollRecords(int page, int size) {
		Page<PayrollRun> payrolls = payrollRunRepository.findAll(PageRequest.of(page, size));
		return payrolls.map(this::toResponseDTO);
	}

	@Override
	public PayrollRunResponseDTO getPayrollById(String payrollRunId) {
		PayrollRun payrollRun = payrollRunRepository.findById(payrollRunId)
				.orElseThrow(() -> new ResourceNotFoundException("Payroll not found for Id : " + payrollRunId));

//		PayrollRunResponseDTO response = new PayrollRunResponseDTO();

		return toResponseDTO(payrollRun);
	}

	@Override
	public Page<PayrollRunResponseDTO> searchPayrollRun(String search, int page, int size) {

		Specification<PayrollRun> specification = Specification.where(PayrollRunSpecification.search(search));

		Page<PayrollRun> payrolls = payrollRunRepository.findAll(specification, PageRequest.of(page, size));
		return payrolls.map(this::toResponseDTO);

	}

	@Override
	public PayrollRunResponseDTO getLatestPayroll() {
		PayrollRun payrollRun = payrollRunRepository.findFirstByOrderByPayrollYearDescPayrollMonthDesc()
				.orElseThrow(() -> new ResourceNotFoundException(""));
		return toResponseDTO(payrollRun);
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
//		payrollRun = payrollRunRepository.save(payrollRun);
		payrollRun.setRemarks(
				"Payroll processed successfully. Processed: " + processedRequests + ", Skipped: " + skippedRequests);

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
		response.setRemarks(
				"Payroll processed successfully. Processed: " + processedRequests + ", Skipped: " + skippedRequests);

		System.out.println("Skipped Requests :" + skippedRequests);
		System.out.println("Processed Requests :" + processedRequests);
		System.out.println("Total Donation Amount : " + totalDonationAmount);

		return response;
	}

	private PayrollRunResponseDTO toResponseDTO(PayrollRun payrollRun) {

		PayrollRunResponseDTO response = new PayrollRunResponseDTO();

		response.setPayrollRunId(payrollRun.getPayrollRunId());
		response.setPayrollMonth(payrollRun.getPayrollMonth());
		response.setPayrollYear(payrollRun.getPayrollYear());
		response.setRunStatus(payrollRun.getRunStatus());
		response.setProcessedOn(payrollRun.getProcessedOn());
		response.setRemarks(payrollRun.getRemarks());

		return response;
	}

}
