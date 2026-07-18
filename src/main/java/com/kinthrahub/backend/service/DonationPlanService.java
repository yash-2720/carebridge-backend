package com.kinthrahub.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kinthrahub.backend.dto.response.DonationPlanResponseDTO;

public interface DonationPlanService {

	public Page<DonationPlanResponseDTO> getAllDonationPlan(int page, int size, boolean isActive, String sortOrder);

	public DonationPlanResponseDTO getDonationPlanById(String id);

	public Page<DonationPlanResponseDTO> searchDonationPlan(String search, boolean isActive, int page, int size,
			String sortOrder);
	
	public List<DonationPlanResponseDTO> getDonationPlansByHospital(String hospitalId, boolean isActive);
}
