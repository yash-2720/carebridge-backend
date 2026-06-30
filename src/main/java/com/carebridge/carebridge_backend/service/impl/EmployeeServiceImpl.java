package com.carebridge.carebridge_backend.service.impl;

import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.request.EmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.response.EmployeeResponseDTO;
import com.carebridge.carebridge_backend.entity.Employee;
import com.carebridge.carebridge_backend.mapper.EmployeeMapper;
import com.carebridge.carebridge_backend.repository.EmployeeRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper;
	private SequenceGenerator sequenceGenerator;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
			SequenceGenerator sequenceGenerator) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
		this.sequenceGenerator = sequenceGenerator;
	}

	public EmployeeResponseDTO addNewEmployee(EmployeeRequestDTO request) {
		Employee employee = employeeMapper.toEntity(request);
		employee.setEmployeeId(sequenceGenerator.generateId("EMP"));
		employeeRepository.save(employee);
		return employeeMapper.toResponseDTO(employee);
	}

}
