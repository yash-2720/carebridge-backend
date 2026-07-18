package com.kinthrahub.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.request.ApplicationUserRequestDTO;
import com.kinthrahub.backend.dto.response.ApplicationUserResponseDTO;
import com.kinthrahub.backend.entity.ApplicationUser;

@Component
public class ApplicationUserMapper {
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
	public ApplicationUser toEntity(ApplicationUserRequestDTO request) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(request.getUsername());
//		applicationUser.setPassword(passwordEncoder.encode(request.getPassword()));
		return applicationUser;
	}
	
	public ApplicationUserResponseDTO toResponseDTO(ApplicationUser applicationUser) {
		ApplicationUserResponseDTO response = new ApplicationUserResponseDTO();
		response.setUserId(applicationUser.getUserId());
		response.setUsername(applicationUser.getUsername());
		response.setLastLogin(applicationUser.getLastLogin());
		response.setEmployeeId(applicationUser.getEmployee().getEmployeeId());
		response.setEmployeeName(applicationUser.getEmployee().getEmployeeName());
		response.setRoleId(applicationUser.getRole().getRoleId());
		response.setRoleName(applicationUser.getRole().getRoleName());
		response.setActive(applicationUser.isActive());
		
		return response;
		
	}

}
