package com.carebridge.carebridge_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmployeeRequestDTO {
	
	@NotBlank
	private String employeeName;

	@Email
	@NotBlank
	private String employeeEmail;

	@NotBlank
	private String employeePhoneNumber;

}
