package com.kinthrahub.backend.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.response.MyDonationSummaryResponseDTO;
import com.kinthrahub.backend.repository.DonationRequestRepository;
import com.kinthrahub.backend.repository.MyDonationSummaryProjection;
import com.kinthrahub.backend.security.LoggedInUserService;
import com.kinthrahub.backend.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private LoggedInUserService loggedInUser;
	private DonationRequestRepository donationRequestRepository;

	DashboardServiceImpl(LoggedInUserService loggedInUser, DonationRequestRepository donationRequestRepository) {
		this.loggedInUser = loggedInUser;
		this.donationRequestRepository = donationRequestRepository;
	}

	@Override
	public MyDonationSummaryResponseDTO getMyDonationSummary() {
		// TODO Auto-generated method stub

		var currentEmployee = loggedInUser.getCurrentEmployee();

		MyDonationSummaryProjection projection = donationRequestRepository.getMyDonationSummary(currentEmployee);

		MyDonationSummaryResponseDTO response = new MyDonationSummaryResponseDTO();

		response.setActiveDonations(projection.getActiveDonations());

		response.setTotalDonationAmount(
				projection.getTotalDonationAmount() != null ? projection.getTotalDonationAmount() : BigDecimal.ZERO);
		;
		return response;
	}

	@Override
	public MyDonationSummaryResponseDTO getDonationRequestSummary() {
		// TODO Auto-generated method stub
		MyDonationSummaryProjection projection = donationRequestRepository.getDonationRequestSummary();
		MyDonationSummaryResponseDTO response = new MyDonationSummaryResponseDTO();

		response.setActiveDonations(projection.getActiveDonations());

		response.setTotalDonationAmount(
				projection.getTotalDonationAmount() != null ? projection.getTotalDonationAmount() : BigDecimal.ZERO);
		;
		return response;
		
	}

}
