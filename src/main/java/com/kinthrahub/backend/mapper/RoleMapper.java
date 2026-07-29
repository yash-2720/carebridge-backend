package com.kinthrahub.backend.mapper;

import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.response.RoleResponseDTO;
import com.kinthrahub.backend.entity.Role;

@Component
public class RoleMapper {
	public RoleResponseDTO toResponseDTO(Role role) {
		RoleResponseDTO dto = new RoleResponseDTO();
		
		dto.setRoleId(role.getRoleId());
		dto.setRoleName(role.getRoleName());
		
		return dto;
	}
}
