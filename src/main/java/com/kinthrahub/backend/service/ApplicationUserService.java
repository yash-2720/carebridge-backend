package com.kinthrahub.backend.service;

import org.springframework.data.domain.Page;

import com.kinthrahub.backend.dto.request.ApplicationUserRequestDTO;
import com.kinthrahub.backend.dto.request.UpdateApplicationUserRequestDTO;
import com.kinthrahub.backend.dto.response.ApplicationUserResponseDTO;

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
