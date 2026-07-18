package com.kinthrahub.backend.authentication.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.authentication.AuthenticationService;
import com.kinthrahub.backend.authentication.dto.LoginRequestDTO;
import com.kinthrahub.backend.authentication.dto.LoginResponseDTO;
import com.kinthrahub.backend.repository.ApplicationUserRepository;
import com.kinthrahub.backend.security.CustomUserDetails;
import com.kinthrahub.backend.security.jwt.JwtService;

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