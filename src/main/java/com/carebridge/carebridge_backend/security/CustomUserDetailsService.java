package com.carebridge.carebridge_backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.repository.ApplicationUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final ApplicationUserRepository applicationUserRepository;

	public CustomUserDetailsService(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("Username received: " + username);

		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//		System.out.println("Database username: " + applicationUser.getUsername());
//		System.out.println("Database password: " + applicationUser.getPassword());
//		System.out.println("Role: " + applicationUser.getRole().getRoleName());

		return new CustomUserDetails(applicationUser);
	}
}