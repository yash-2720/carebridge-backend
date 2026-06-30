package com.carebridge.carebridge_backend.service;

import com.carebridge.carebridge_backend.dto.request.EmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.response.EmployeeResponseDTO;

public interface EmployeeService {
	
	public EmployeeResponseDTO addNewEmployee(EmployeeRequestDTO request) ;

}
