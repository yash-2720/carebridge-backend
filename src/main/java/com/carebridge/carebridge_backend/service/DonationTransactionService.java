package com.carebridge.carebridge_backend.service;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.response.DonationTransactionResponseDTO;

public interface DonationTransactionService {
	
	public DonationTransactionResponseDTO getTransactionById(String donationTransactionId);
	
	public Page<DonationTransactionResponseDTO> getAllDonationTransactions(int page, int size, String sortOrder);
	
	public Page<DonationTransactionResponseDTO> SearchDonationTransactions(String search, int page, int size, boolean isActive);
	
	public Page<DonationTransactionResponseDTO> getTransactionsByDonationRequest(String donationRequestId, int page, int size);
	
}
