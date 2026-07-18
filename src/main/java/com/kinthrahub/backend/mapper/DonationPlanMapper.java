package com.kinthrahub.backend.mapper;

import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.response.DonationPlanResponseDTO;
import com.kinthrahub.backend.entity.DonationPlan;

@Component
public class DonationPlanMapper {
	
	public DonationPlanResponseDTO toResponseDTO(DonationPlan donationPlan) {
		
		DonationPlanResponseDTO response = new DonationPlanResponseDTO();
		response.setDonationPlanId(donationPlan.getDonationPlanId());
		response.setDonationName(donationPlan.getDonationName());
		response.setDonationDescription(donationPlan.getDonationDescription());
		response.setHospitalId(donationPlan.getHospital().getHospitalId());
		response.setHospitalName(donationPlan.getHospital().getHospitalName());
		response.setActive(donationPlan.isActive());
		
		return response;
		
	}

}
