package com.kinthrahub.backend.mapper;

import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.request.EmployeeRequestDTO;
import com.kinthrahub.backend.dto.request.UpdateEmployeeRequestDTO;
import com.kinthrahub.backend.dto.response.CurrentEmployeeResponseDTO;
import com.kinthrahub.backend.dto.response.EmployeeResponseDTO;
import com.kinthrahub.backend.entity.Employee;

@Component
public class EmployeeMapper {

	public Employee toEntity(EmployeeRequestDTO request) {

		Employee employee = new Employee();

		employee.setEmployeeNumber(request.getEmployeeNumber());
		employee.setEmployeeName(request.getEmployeeName());
		employee.setEmployeeEmail(request.getEmployeeEmail());
		employee.setEmployeePhoneNumber(request.getEmployeePhoneNumber());
		employee.setBasicSalary(request.getBasicSalary());

		return employee;
	}

	public EmployeeResponseDTO toResponseDTO(Employee employee) {
		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setEmployeeId(employee.getEmployeeId());
		response.setEmployeeName(employee.getEmployeeName());
		response.setEmployeeEmail(employee.getEmployeeEmail());
		response.setEmployeeNumber(employee.getEmployeeNumber());
		response.setEmployeePhoneNumber(employee.getEmployeePhoneNumber());
		response.setBasicSalary(employee.getBasicSalary());
		response.setActive(employee.isActive());

		return response;
	}
	
	public CurrentEmployeeResponseDTO toCurrentEmployeeResponseDTO(Employee employee) {
		CurrentEmployeeResponseDTO response = new CurrentEmployeeResponseDTO();
		response.setEmployeeId(employee.getEmployeeId());
		response.setBasicSalary(employee.getBasicSalary());
		
		return response;
	}

	public void updateEntity(Employee employee, UpdateEmployeeRequestDTO request) {

		employee.setEmployeeName(request.getEmployeeName());
		employee.setEmployeeEmail(request.getEmployeeEmail());
		employee.setEmployeePhoneNumber(request.getEmployeePhoneNumber());

	}

}
