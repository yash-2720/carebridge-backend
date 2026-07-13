package com.carebridge.carebridge_backend.authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_backend.authentication.dto.LoginRequestDTO;
import com.carebridge.carebridge_backend.authentication.dto.LoginResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	private AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	
	@PostMapping("/login")
	public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO request) {
		return authenticationService.login(request);
	}
}
