package com.carebridge.carebridge_backend.service.impl;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.request.DonationRequestDTO;
import com.carebridge.carebridge_backend.dto.response.DonationRequestResponseDTO;
import com.carebridge.carebridge_backend.entity.DonationPlan;
import com.carebridge.carebridge_backend.entity.DonationRequest;
import com.carebridge.carebridge_backend.entity.Employee;
import com.carebridge.carebridge_backend.enums.DonationStatus;
import com.carebridge.carebridge_backend.exception.BusinessValidationException;
import com.carebridge.carebridge_backend.exception.ResourceNotFoundException;
import com.carebridge.carebridge_backend.mapper.DonationRequestMapper;
import com.carebridge.carebridge_backend.repository.DonationPlanRepository;
import com.carebridge.carebridge_backend.repository.DonationRequestRepository;
import com.carebridge.carebridge_backend.repository.EmployeeRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.DonationRequestService;
import com.carebridge.carebridge_backend.specification.DonationRequestSpecification;

@Service
public class DonationRequestServiceImpl implements DonationRequestService {

	private DonationRequestRepository donationRequestRepository;
	private SequenceGenerator sequenceGenerator;
	private EmployeeRepository employeeRepository;
	private DonationPlanRepository donationPlanRepository;
	private DonationRequestMapper donationRequestMapper;

	public DonationRequestServiceImpl(DonationRequestRepository donationRequestRepository,
			SequenceGenerator sequenceGenerator, EmployeeRepository employeeRepository,
			DonationPlanRepository donationPlanRepository, DonationRequestMapper donationRequestMapper) {

		this.donationRequestRepository = donationRequestRepository;
		this.sequenceGenerator = sequenceGenerator;
		this.employeeRepository = employeeRepository;
		this.donationPlanRepository = donationPlanRepository;
		this.donationRequestMapper = donationRequestMapper;
	}

	public DonationRequestResponseDTO addDonationRequest(DonationRequestDTO request) {

		DonationRequest donationRequest = donationRequestMapper.toEntity(request);

		Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for Id : " + request.getEmployeeId()));
		DonationPlan donationPlan = donationPlanRepository.findById(request.getDonationPlanId()).orElseThrow(
				() -> new ResourceNotFoundException("Donation Plan not found for Id : " + request.getDonationPlanId()));

		donationRequest.setEmployee(employee);
		donationRequest.setDonationPlan(donationPlan);

		donationRequest.setDonationRequestId(sequenceGenerator.generateId("DOR"));

		BigDecimal salary = employee.getBasicSalary();
		BigDecimal donationAmount = donationRequest.getDonationAmount();
		BigDecimal updatedAmount = salary.subtract(donationAmount);
		if (donationAmount.compareTo(BigDecimal.valueOf(500)) < 0) {
			throw new BusinessValidationException("The minimum donation amount allowed is 500");
		}

		if (updatedAmount.compareTo(BigDecimal.valueOf(5000)) < 0) {
			throw new BusinessValidationException(
					"Insufficient remaining salary. Your account must retain at least 5,000 after donating.");

		}
		donationRequest.setDonationStatus(DonationStatus.INITIALIZED);
		donationRequestRepository.save(donationRequest);
		return donationRequestMapper.toResponseDTO(donationRequest);
	}

	public DonationRequestResponseDTO getDonationRequestById(String donationRequestId) {
		DonationRequest donationRequest = donationRequestRepository.findById(donationRequestId).orElseThrow(
				() -> new ResourceNotFoundException("Donation Request Not found for Id : " + donationRequestId));

		return donationRequestMapper.toResponseDTO(donationRequest);
	}

	public Page<DonationRequestResponseDTO> getAllDonationRequests(boolean isActive, int page, int size) {
		Specification<DonationRequest> specification = Specification
				.where(DonationRequestSpecification.isActive(isActive));

		Page<DonationRequest> donationRequests = donationRequestRepository.findAll(specification,
				PageRequest.of(page, size));
		return donationRequests.map(donationRequestMapper::toResponseDTO);
	}

	public Page<DonationRequestResponseDTO> searchDonationRequests(String search, boolean isActive, int page,
			int size) {
		
		Specification<DonationRequest> specification = Specification.where(DonationRequestSpecification.search(search))
				.and(DonationRequestSpecification.isActive(isActive));
		
		Page<DonationRequest> donationRequests = donationRequestRepository.findAll(specification,
				PageRequest.of(page, size));
		return donationRequests.map(donationRequestMapper::toResponseDTO);
	}

	
	public DonationRequestResponseDTO cancelDonationRequest(String donationRequestId) {
		
		DonationRequest donationRequest = donationRequestRepository.findById(donationRequestId).orElseThrow(
				() -> new ResourceNotFoundException("Donation Request not found for Id : " + donationRequestId));
		if(donationRequest.getDonationStatus() == DonationStatus.CANCELLED) {
			throw new BusinessValidationException("Donation Request Already Cancelled");
		}
		donationRequest.setDonationStatus(DonationStatus.CANCELLED);
		donationRequest.setActive(false);
		donationRequestRepository.save(donationRequest);
		return donationRequestMapper.toResponseDTO(donationRequest);
	}

}
