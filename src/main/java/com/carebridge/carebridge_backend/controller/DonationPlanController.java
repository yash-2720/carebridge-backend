package com.carebridge.carebridge_backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_backend.dto.response.DonationPlanResponseDTO;
import com.carebridge.carebridge_backend.service.DonationPlanService;

@RestController
@RequestMapping("/donationPlan")
public class DonationPlanController {

	private DonationPlanService donationPlanService;

	public DonationPlanController(DonationPlanService donationPlanService) {
		this.donationPlanService = donationPlanService;
	}

	@GetMapping("/getById/{id}")
	public DonationPlanResponseDTO getDonationPlanById(@PathVariable String id) {
		return donationPlanService.getDonationPlanById(id);
	}

	@GetMapping("/getAllDonatonPlan")
	public Page<DonationPlanResponseDTO> getAllDonatinPlans(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "true") boolean isActive) {

		return donationPlanService.getAllDonationPlan(page, size, isActive);

	}

	@GetMapping("/search")
	public Page<DonationPlanResponseDTO> search(@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "asc") String sortOrder,
			@RequestParam(defaultValue = "true") boolean isActive) {
		return donationPlanService.searchDonationPlan(search, isActive, page, size, sortOrder);
	}

	@GetMapping("/getDonationPlanByHospitalId/{id}")
	public List<DonationPlanResponseDTO> getDonationPlansByHospital(@PathVariable String id,
			@RequestParam(defaultValue = "true") boolean isActive) {
		return donationPlanService.getDonationPlansByHospital(id, isActive);
	}
}
