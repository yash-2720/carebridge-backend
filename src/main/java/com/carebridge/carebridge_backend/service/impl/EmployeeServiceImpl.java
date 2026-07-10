package com.carebridge.carebridge_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.request.EmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.request.UpdateEmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.response.EmployeeResponseDTO;
import com.carebridge.carebridge_backend.entity.Employee;
import com.carebridge.carebridge_backend.exception.BusinessValidationException;
import com.carebridge.carebridge_backend.exception.ResourceNotFoundException;
import com.carebridge.carebridge_backend.mapper.EmployeeMapper;
import com.carebridge.carebridge_backend.repository.EmployeeRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.EmployeeService;
import com.carebridge.carebridge_backend.specification.EmployeeSpecification;

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

	@Override
	public EmployeeResponseDTO addNewEmployee(EmployeeRequestDTO request) {

		if (employeeRepository.existsByEmployeeNumber(request.getEmployeeNumber())) {
			throw new BusinessValidationException(
					"Employee already exists with employee number : " + request.getEmployeeNumber());
		}

		if (employeeRepository.existsByEmployeePhoneNumber(request.getEmployeePhoneNumber())) {
			throw new BusinessValidationException(
					"Employee already exists with Phone number : " + request.getEmployeePhoneNumber());
		}
		if (employeeRepository.existsByEmployeeEmail(request.getEmployeeEmail())) {
			throw new BusinessValidationException(
					"Employee already exists with Email Id : " + request.getEmployeeEmail());
		}

		Employee employee = employeeMapper.toEntity(request);
		employee.setEmployeeId(sequenceGenerator.generateId("EMP"));
		employeeRepository.save(employee);
		return employeeMapper.toResponseDTO(employee);
	}

	@Override
	public EmployeeResponseDTO getEmployeeById(String id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return employeeMapper.toResponseDTO(employee);
	}

	@Override
	public Page<EmployeeResponseDTO> getAllEmployees(boolean isActive, int page, int size, String sortOrder) {
		Specification<Employee> specification = Specification.where(EmployeeSpecification.isActive(isActive));
		Sort sort;
		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "employeeName");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "employeeName");
		}

		Page<Employee> employees = employeeRepository.findAll(specification, PageRequest.of(page, size, sort));
		return employees.map(employeeMapper::toResponseDTO);

	}

	@Override
	public EmployeeResponseDTO softDeleteEmployee(String id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		employee.setActive(false);
		employeeRepository.save(employee);
		return employeeMapper.toResponseDTO(employee);
	}

	@Override
	public EmployeeResponseDTO updateEmployee(String id, UpdateEmployeeRequestDTO request) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		employeeMapper.updateEntity(employee, request);
		employeeRepository.save(employee);
		return employeeMapper.toResponseDTO(employee);

	}

	@Override
	public Page<EmployeeResponseDTO> searchEmployee(String search, boolean isActive, int page, int size) {
		Specification<Employee> specification = Specification.where(EmployeeSpecification.search(search))
				.and(EmployeeSpecification.isActive(isActive));
		Page<Employee> employees = employeeRepository.findAll(specification, PageRequest.of(page, size));
		return employees.map(employeeMapper::toResponseDTO);
	}

}
