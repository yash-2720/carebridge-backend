package com.kinthrahub.backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.response.DonationTransactionResponseDTO;
import com.kinthrahub.backend.entity.DonationRequest;
import com.kinthrahub.backend.entity.DonationTransaction;
import com.kinthrahub.backend.entity.PayrollRun;
import com.kinthrahub.backend.exception.ResourceNotFoundException;
import com.kinthrahub.backend.mapper.DonationTransactionMapper;
import com.kinthrahub.backend.repository.DonationRequestRepository;
import com.kinthrahub.backend.repository.DonationTransactionRepository;
import com.kinthrahub.backend.repository.PayrollRunRepository;
import com.kinthrahub.backend.security.LoggedInUserService;
import com.kinthrahub.backend.service.DonationTransactionService;
import com.kinthrahub.backend.specification.DonationTransactionSpecification;

@Service
public class DonationTransactionServiceImpl implements DonationTransactionService {

	private DonationTransactionMapper donationTransactionMapper;
	private DonationTransactionRepository donationTransactionRepository;

	private DonationRequestRepository donationRequestRepository;
	private PayrollRunRepository payrollRunRepository;

	private LoggedInUserService loggedInUserService;

	public DonationTransactionServiceImpl(DonationTransactionMapper donationTransactionMapper,
			DonationTransactionRepository donationTransactionRepository,
			DonationRequestRepository donationRequestRepository, PayrollRunRepository payrollRunRepository,
			LoggedInUserService loggedInUserService) {

		this.donationTransactionMapper = donationTransactionMapper;
		this.donationTransactionRepository = donationTransactionRepository;
		this.donationRequestRepository = donationRequestRepository;
		this.payrollRunRepository = payrollRunRepository;
		this.loggedInUserService = loggedInUserService;

	}

	@Override
	public DonationTransactionResponseDTO getTransactionById(String donationTransactionId) {
		DonationTransaction donationTransaction;
		if (loggedInUserService.isAdmin()) {
			donationTransaction = donationTransactionRepository.findById(donationTransactionId)
					.orElseThrow(() -> new ResourceNotFoundException(
							"Donation Transaction Not found for Id : " + donationTransactionId));
		} else {
			donationTransaction = donationTransactionRepository
					.findByDonationTransactionIdAndDonationRequestEmployeeEmployeeId(donationTransactionId,
							loggedInUserService.getCurrentEmployee().getEmployeeId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Donation Transaction Not found for Id :" + donationTransactionId));
		}
		return donationTransactionMapper.toResponseDTO(donationTransaction);
	}

	@Override
	public Page<DonationTransactionResponseDTO> getAllDonationTransactions(int page, int size, String sortOrder) {

		Specification<DonationTransaction> specification = null;

		if (!loggedInUserService.isAdmin()) {
			specification = DonationTransactionSpecification.employee(loggedInUserService.getCurrentEmployee());
		}
		Page<DonationTransaction> donationTransactions = donationTransactionRepository.findAll(specification,
				PageRequest.of(page, size));
		return donationTransactions.map(donationTransactionMapper::toResponseDTO);

	}

	@Override
	public Page<DonationTransactionResponseDTO> searchDonationTransactions(String search, int page, int size,
			boolean isActive) {

		Specification<DonationTransaction> specification = Specification
				.where(DonationTransactionSpecification.search(search))
				.and(DonationTransactionSpecification.isActive(isActive));
		if (!loggedInUserService.isAdmin()) {
			specification = specification
					.and(DonationTransactionSpecification.employee(loggedInUserService.getCurrentEmployee()));
		}

		Page<DonationTransaction> donationTransactions = donationTransactionRepository.findAll(specification,
				PageRequest.of(page, size));
		return donationTransactions.map(donationTransactionMapper::toResponseDTO);

	}

	@Override
	public Page<DonationTransactionResponseDTO> getTransactionsByDonationRequest(String donationRequestId, int page,
			int size) {

		DonationRequest donationRequest;
		if (loggedInUserService.isAdmin()) {
			donationRequest = donationRequestRepository.findById(donationRequestId).orElseThrow(
					() -> new ResourceNotFoundException("Donation Request not found for Id : " + donationRequestId));
		} else {
			donationRequest = donationRequestRepository
					.findByDonationRequestIdAndEmployeeEmployeeId(donationRequestId,
							loggedInUserService.getCurrentEmployee().getEmployeeId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Donation Request not found for Id : " + donationRequestId));

		}
		Page<DonationTransaction> donationTransactions = donationTransactionRepository
				.findByDonationRequestDonationRequestId(donationRequest.getDonationRequestId(),
						PageRequest.of(page, size));
		return donationTransactions.map(donationTransactionMapper::toResponseDTO);
	}

	@Override
	public Page<DonationTransactionResponseDTO> getTransactionsByPayrollRunId(String payrollRunId, int page, int size) {

		PayrollRun payrollRun = payrollRunRepository.findById(payrollRunId)
				.orElseThrow(() -> new ResourceNotFoundException("Payroll not found for Id : " + payrollRunId));

		Specification<DonationTransaction> specification = Specification
				.where(DonationTransactionSpecification.payrollRun(payrollRun.getPayrollRunId()));

		if (!loggedInUserService.isAdmin()) {

			specification = specification
					.and(DonationTransactionSpecification.employee(loggedInUserService.getCurrentEmployee()));
		}

		Page<DonationTransaction> donationTransactions = donationTransactionRepository.findAll(specification,
				PageRequest.of(page, size));

		return donationTransactions.map(donationTransactionMapper::toResponseDTO);
	}

}
