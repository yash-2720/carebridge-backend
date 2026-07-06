package com.carebridge.carebridge_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.response.DonationTransactionResponseDTO;
import com.carebridge.carebridge_backend.entity.DonationPlan;
import com.carebridge.carebridge_backend.entity.DonationRequest;
import com.carebridge.carebridge_backend.entity.DonationTransaction;
import com.carebridge.carebridge_backend.exception.ResourceNotFoundException;
import com.carebridge.carebridge_backend.mapper.DonationRequestMapper;
import com.carebridge.carebridge_backend.mapper.DonationTransactionMapper;
import com.carebridge.carebridge_backend.repository.DonationRequestRepository;
import com.carebridge.carebridge_backend.repository.DonationTransactionRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.DonationTransactionService;
import com.carebridge.carebridge_backend.specification.DonationTransactionSpecification;

@Service
public class DonationTransactionServiceImpl implements DonationTransactionService {

	private SequenceGenerator sequenceGenerator;
	private DonationTransactionMapper donationTransactionMapper;
	private DonationTransactionRepository donationTransactionRepository;

	private DonationRequestRepository donationRequestRepository;

	public DonationTransactionServiceImpl(SequenceGenerator sequenceGenerator,
			DonationTransactionMapper donationTransactionMapper,
			DonationTransactionRepository donationTransactionRepository,
			DonationRequestRepository donationRequestRepository) {
		this.sequenceGenerator = sequenceGenerator;
		this.donationTransactionMapper = donationTransactionMapper;
		this.donationTransactionRepository = donationTransactionRepository;
		this.donationRequestRepository = donationRequestRepository;
	}

	@Override
	public DonationTransactionResponseDTO getTransactionById(String donationTransactionId) {
		DonationTransaction donationTransaction = donationTransactionRepository.findById(donationTransactionId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Donation Transaction Not found for Id : " + donationTransactionId));
		return donationTransactionMapper.toResponseDTO(donationTransaction);
	}

	@Override
	public Page<DonationTransactionResponseDTO> getAllDonationTransactions(int page, int size, String sortOrder) {
//		Specification<DonationTransaction> specification = Specification
//				.where(DonationTransactionSpecification.isActive(isActive));
//		List<DonationTransaction> 

//		Sort sort;
//		if ("desc".equalsIgnoreCase(sortOrder)) {
//			sort = Sort.by(Sort.Direction.DESC, "username");
//		} else {
//			sort = Sort.by(Sort.Direction.ASC, "username");
//		}
		Page<DonationTransaction> donationTransactions = donationTransactionRepository.findAll(PageRequest.of(page, size));
		return donationTransactions.map(donationTransactionMapper::toResponseDTO);

	}

	@Override
	public Page<DonationTransactionResponseDTO> SearchDonationTransactions(String search, int page, int size,
			boolean isActive) {

		Specification<DonationTransaction> specification = Specification
				.where(DonationTransactionSpecification.search(search))
				.and(DonationTransactionSpecification.isActive(isActive));

		Page<DonationTransaction> donationTransactions = donationTransactionRepository.findAll(specification,
				PageRequest.of(page, size));
		return donationTransactions.map(donationTransactionMapper::toResponseDTO);

	}

	@Override
	public Page<DonationTransactionResponseDTO> getTransactionsByDonationRequest(String donationRequestId, int page,
			int size) {

		DonationRequest donationRequest = donationRequestRepository.findById(donationRequestId).orElseThrow(
				() -> new ResourceNotFoundException("Donation Request not found for Id : " + donationRequestId));
		Page<DonationTransaction> donationTransaction = donationTransactionRepository
				.findByDonationRequestDonationRequestId(donationRequestId, PageRequest.of(page, size));
//		return donationTransactions.map(donationTransactionMapper:: toResponseDTO);
		return donationTransaction.map(donationTransactionMapper::toResponseDTO);
	}

}
