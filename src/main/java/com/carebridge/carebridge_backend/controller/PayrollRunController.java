package com.carebridge.carebridge_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_backend.dto.request.PayrollRunRequestDTO;
import com.carebridge.carebridge_backend.dto.response.PayrollRunResponseDTO;
import com.carebridge.carebridge_backend.service.PayrollRunService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payRollRun")
public class PayrollRunController {
	
	private PayrollRunService payrollRunService;
	
	public PayrollRunController(PayrollRunService payrollRunService) {
		this.payrollRunService = payrollRunService;
	}
	
	@PostMapping
	public PayrollRunResponseDTO runPayroll(@RequestBody @Valid PayrollRunRequestDTO request) {
		return payrollRunService.runPayroll(request);
	}
}
