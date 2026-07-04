package com.carebridge.carebridge_backend.service;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.request.DonationRequestDTO;
import com.carebridge.carebridge_backend.dto.response.DonationRequestResponseDTO;

public interface DonationRequestService {
	
	DonationRequestResponseDTO addDonationRequest(DonationRequestDTO request);

	DonationRequestResponseDTO getDonationRequestById(String donationRequestId);

	Page<DonationRequestResponseDTO> getAllDonationRequests(
	        boolean isActive,
	        int page,
	        int size);

	Page<DonationRequestResponseDTO> searchDonationRequests(
	        String search,
	        boolean isActive,
	        int page,
	        int size);

	DonationRequestResponseDTO cancelDonationRequest(String donationRequestId);

}
