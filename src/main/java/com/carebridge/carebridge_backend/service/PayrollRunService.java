package com.carebridge.carebridge_backend.service;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.request.PayrollRunRequestDTO;
import com.carebridge.carebridge_backend.dto.response.PayrollRunResponseDTO;

public interface PayrollRunService {

    PayrollRunResponseDTO runPayroll(PayrollRunRequestDTO request);
    
    Page<PayrollRunResponseDTO> getAllPayrollRecords(int page, int size);

    public PayrollRunResponseDTO getPayrollById(String payrollRunId);
    
    public Page<PayrollRunResponseDTO> searchPayrollRun(String search, int page, int size);
}