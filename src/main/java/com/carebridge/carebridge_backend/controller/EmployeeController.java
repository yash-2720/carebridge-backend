package com.carebridge.carebridge_backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_backend.dto.request.EmployeeRequestDTO;
import com.carebridge.carebridge_backend.dto.response.EmployeeResponseDTO;
import com.carebridge.carebridge_backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/addEmployee")
	public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO request) {

		return employeeService.addNewEmployee(request);

	}

	@GetMapping("/getById/{id}")
	public EmployeeResponseDTO getEmployeeById(@PathVariable String id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/getAllEmployees")
	public List<EmployeeResponseDTO> getAllEmployees(@RequestParam(defaultValue = "true") boolean isActive) {
		return employeeService.getAllEmployees(isActive);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public EmployeeResponseDTO softDeleteEmployee(@PathVariable String id) {
		return employeeService.softDeleteEmployee(id);

	}
	
	@PutMapping("/updateEmployee/{id}")
	public EmployeeResponseDTO updateEmployee( @PathVariable String id, @Valid @RequestBody EmployeeRequestDTO request) {
		return employeeService.updateEmployee(id, request);
	}
	
	@GetMapping("/search")
	public Page<EmployeeResponseDTO> getEmployees(
	        @RequestParam(required = false) String search,
	        @RequestParam(defaultValue = "true") boolean isActive,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    return employeeService.searchEmployee(search, isActive, page, size);
	}

}
