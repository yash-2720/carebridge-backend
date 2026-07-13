package com.carebridge.carebridge_backend.authentication.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.authentication.AuthenticationService;
import com.carebridge.carebridge_backend.authentication.dto.LoginRequestDTO;
import com.carebridge.carebridge_backend.authentication.dto.LoginResponseDTO;
import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.repository.ApplicationUserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserRepository applicationUserRepository;

	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			ApplicationUserRepository applicationUserRepository) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
//		ApplicationUser user = applicationUserRepository.findByUsername(request.getUsername()).orElseThrow();
//
//		System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()));
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		System.out.println(authentication.getName());
		System.out.println(authentication.isAuthenticated());
		System.out.println(authentication.getAuthorities());
		System.out.println(authentication.getPrincipal());

		return null;
	}
}