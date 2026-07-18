package com.kinthrahub.backend.service;

import org.springframework.data.domain.Page;

import com.kinthrahub.backend.dto.request.PayrollRunRequestDTO;
import com.kinthrahub.backend.dto.response.PayrollRunResponseDTO;

public interface PayrollRunService {

    PayrollRunResponseDTO runPayroll(PayrollRunRequestDTO request);
    
    Page<PayrollRunResponseDTO> getAllPayrollRecords(int page, int size);

    public PayrollRunResponseDTO getPayrollById(String payrollRunId);
    
    public Page<PayrollRunResponseDTO> searchPayrollRun(String search, int page, int size);
}