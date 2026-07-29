package com.kinthrahub.backend.service;

import java.util.List;

import com.kinthrahub.backend.dto.response.RoleResponseDTO;

public interface RoleService {
	
	public List<RoleResponseDTO> getActiveRoles();

}
