package com.kinthrahub.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinthrahub.backend.dto.request.PayrollRunRequestDTO;
import com.kinthrahub.backend.dto.response.PayrollRunResponseDTO;
import com.kinthrahub.backend.service.PayrollRunService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payRollRun")
public class PayrollRunController {
	
	private PayrollRunService payrollRunService;
	
	public PayrollRunController(PayrollRunService payrollRunService) {
		this.payrollRunService = payrollRunService;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','PAYROLL_ADMIN')")
	@PostMapping("/executePayroll")
	public PayrollRunResponseDTO runPayroll(@RequestBody @Valid PayrollRunRequestDTO request) {
		return payrollRunService.runPayroll(request);
	}
	

	@PreAuthorize("hasAnyRole('ADMIN','PAYROLL_ADMIN')")
	@GetMapping("/getAllPayroll")
	Page<PayrollRunResponseDTO> getAllPayrollRecords(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		return payrollRunService.getAllPayrollRecords(page, size);
	}


	@PreAuthorize("hasAnyRole('ADMIN','PAYROLL_ADMIN')")
	@GetMapping("/getPayrollById/{id}")
    public PayrollRunResponseDTO getPayrollById(@PathVariable String id) {
    	return payrollRunService.getPayrollById(id);
    }
    

	@PreAuthorize("hasAnyRole('ADMIN','PAYROLL_ADMIN')")
	@GetMapping("/search")
    public Page<PayrollRunResponseDTO> SearchPayrollRun(@RequestParam(required = false) String search,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
    	return payrollRunService.searchPayrollRun(search, page, size);
    }
}
