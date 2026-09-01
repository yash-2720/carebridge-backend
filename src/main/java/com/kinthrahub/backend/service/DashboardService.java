package com.kinthrahub.backend.service;

import com.kinthrahub.backend.dto.response.MyDonationSummaryResponseDTO;

public interface DashboardService {
	MyDonationSummaryResponseDTO getMyDonationSummary();
	
	MyDonationSummaryResponseDTO getDonationRequestSummary();
}
