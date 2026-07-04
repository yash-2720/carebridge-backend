package com.carebridge.carebridge_backend.mapper;

import org.springframework.stereotype.Component;

import com.carebridge.carebridge_backend.dto.request.DonationRequestDTO;
import com.carebridge.carebridge_backend.dto.response.DonationRequestResponseDTO;
import com.carebridge.carebridge_backend.entity.DonationRequest;

@Component
public class DonationRequestMapper {

	public DonationRequest toEntity(DonationRequestDTO request) {
		DonationRequest donationRequest = new DonationRequest();
		donationRequest.setDonationAmount(request.getDonationAmount());
		donationRequest.setDonationEndDate(request.getDonationEndDate());
		donationRequest.setDonationStartDate(request.getDonationStartDate());
		donationRequest.setDonationType(request.getDonationType());
		return donationRequest;
	}

	public DonationRequestResponseDTO toResponseDTO(DonationRequest donationRequest) {
		DonationRequestResponseDTO response = new DonationRequestResponseDTO();
		response.setDonationRequestId(donationRequest.getDonationRequestId());
		response.setEmployeeId(donationRequest.getEmployee().getEmployeeId());
		response.setEmployeeName(donationRequest.getEmployee().getEmployeeName());
		response.setDonationPlanId(donationRequest.getDonationPlan().getDonationPlanId());
		response.setDonationName(donationRequest.getDonationPlan().getDonationName());
		response.setDonationType(donationRequest.getDonationType());
		response.setDonationAmount(donationRequest.getDonationAmount());
		response.setDonationStartDate(donationRequest.getDonationStartDate());
		response.setDonationEndDate(donationRequest.getDonationEndDate());
		response.setDonationStatus(donationRequest.getDonationStatus());
		response.setActive(donationRequest.isActive());
		return response;
	}

}
