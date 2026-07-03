package com.carebridge.carebridge_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicationUserRequestDTO {

	@NotBlank(message = "Employee Id is required")
    private String employeeId;

	@NotBlank(message = "Role Id is required")
    private String roleId;

	@NotBlank(message = "Username is required")
    private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 20,message = "Password must be between 8 and 20 characters")
    private String password;

}
