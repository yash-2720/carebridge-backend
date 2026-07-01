package com.carebridge.carebridge_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.request.EmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.request.UpdateEmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.response.EmployeeResponseDTO;


public interface EmployeeService {
	
	public EmployeeResponseDTO addNewEmployee(EmployeeRequestDTO request) ;
	
	public EmployeeResponseDTO getEmployeeById(String id);
	
	public List<EmployeeResponseDTO> getAllEmployees(boolean isActive);
	
	public EmployeeResponseDTO softDeleteEmployee(String id);
	
	public EmployeeResponseDTO updateEmployee(String id, UpdateEmployeeRequestDTO request);
	
	public Page<EmployeeResponseDTO> searchEmployee(String search, boolean isActive, int page, int size);
}
