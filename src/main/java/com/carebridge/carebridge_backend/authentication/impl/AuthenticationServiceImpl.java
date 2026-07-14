package com.carebridge.carebridge_backend.authentication.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.authentication.AuthenticationService;
import com.carebridge.carebridge_backend.authentication.dto.LoginRequestDTO;
import com.carebridge.carebridge_backend.authentication.dto.LoginResponseDTO;
import com.carebridge.carebridge_backend.repository.ApplicationUserRepository;
import com.carebridge.carebridge_backend.security.CustomUserDetails;
import com.carebridge.carebridge_backend.security.jwt.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		String token = jwtService.generateToken(user);

		LoginResponseDTO response = new LoginResponseDTO();
		response.setToken(token);

		return response;

	}
}