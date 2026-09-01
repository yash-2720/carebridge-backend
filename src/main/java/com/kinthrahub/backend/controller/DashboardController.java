package com.kinthrahub.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinthrahub.backend.dto.response.MyDonationSummaryResponseDTO;
import com.kinthrahub.backend.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	private DashboardService dashboardService;
	
	public DashboardController (DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/myDonationSummary")
	public MyDonationSummaryResponseDTO getMyDonationSummary() {
		return dashboardService.getMyDonationSummary();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/GetDonationSummary")
	public MyDonationSummaryResponseDTO getDonationRequestSummary() {
		return dashboardService.getDonationRequestSummary();
	}
}
