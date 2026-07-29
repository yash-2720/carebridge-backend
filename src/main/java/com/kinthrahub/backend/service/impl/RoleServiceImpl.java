package com.kinthrahub.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.response.RoleResponseDTO;
import com.kinthrahub.backend.mapper.RoleMapper;
import com.kinthrahub.backend.repository.RoleRepository;
import com.kinthrahub.backend.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	public  RoleRepository roleRepository;
	public RoleMapper roleMapper;
	public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
	}

	@Override
	public List<RoleResponseDTO> getActiveRoles() {
		
		return roleRepository.findByIsActiveTrueOrderByRoleNameAsc().stream().map(roleMapper::toResponseDTO).toList();
	}

}
