package com.carebridge.carebridge_backend.authentication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
	
	@NotNull(message = "Username is requried")
	private String username;
	@NotNull(message = "Password is required")
	private String password;
}
