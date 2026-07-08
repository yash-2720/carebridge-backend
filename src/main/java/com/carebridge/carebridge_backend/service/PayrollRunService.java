package com.carebridge.carebridge_backend.service;

import com.carebridge.carebridge_backend.dto.request.PayrollRunRequestDTO;
import com.carebridge.carebridge_backend.dto.response.PayrollRunResponseDTO;

public interface PayrollRunService {

    PayrollRunResponseDTO runPayroll(PayrollRunRequestDTO request);

}