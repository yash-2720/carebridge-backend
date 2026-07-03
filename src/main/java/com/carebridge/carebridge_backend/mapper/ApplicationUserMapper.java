package com.carebridge.carebridge_backend.mapper;

import org.springframework.stereotype.Component;

import com.carebridge.carebridge_backend.dto.request.ApplicationUserRequestDTO;
import com.carebridge.carebridge_backend.dto.response.ApplicationUserResponseDTO;
import com.carebridge.carebridge_backend.entity.ApplicationUser;

@Component
public class ApplicationUserMapper {
	
	public ApplicationUser toEntity(ApplicationUserRequestDTO request) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUsername(request.getUsername());
		applicationUser.setPassword(request.getPassword());
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
