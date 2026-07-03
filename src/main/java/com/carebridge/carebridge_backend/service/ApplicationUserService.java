package com.carebridge.carebridge_backend.service;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.request.ApplicationUserRequestDTO;
import com.carebridge.carebridge_backend.dto.request.UpdateApplicationUserRequestDTO;
import com.carebridge.carebridge_backend.dto.response.ApplicationUserResponseDTO;

public interface ApplicationUserService {

	public ApplicationUserResponseDTO getAppUserById(String id);

	public ApplicationUserResponseDTO addNewAppUser(ApplicationUserRequestDTO request);

	public Page<ApplicationUserResponseDTO> searchApplicationUser(String search, boolean isActive, int page, int size,
			String sortOrder);

	public ApplicationUserResponseDTO softDeleteUser(String id);

	public Page<ApplicationUserResponseDTO> getAllApplicationUsers(int page, int size, String sortOrder,
			boolean isActive);
	
	public ApplicationUserResponseDTO updateUser(UpdateApplicationUserRequestDTO request, String id);
}
