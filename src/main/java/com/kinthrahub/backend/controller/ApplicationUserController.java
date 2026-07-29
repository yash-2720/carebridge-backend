package com.kinthrahub.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinthrahub.backend.dto.request.ApplicationUserRequestDTO;
import com.kinthrahub.backend.dto.request.UpdateApplicationUserRequestDTO;
import com.kinthrahub.backend.dto.response.ApplicationUserResponseDTO;
import com.kinthrahub.backend.service.ApplicationUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applicationUser")
public class ApplicationUserController {

	private ApplicationUserService applicationUserService;

	public ApplicationUserController(ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addNewUser")
	public ApplicationUserResponseDTO addNewAppUser(@RequestBody @Valid ApplicationUserRequestDTO request) {
		return applicationUserService.addNewAppUser(request);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getUserById/{id}")
	public ApplicationUserResponseDTO getAppUserById(@PathVariable String id) {
		return applicationUserService.getAppUserById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/search")
	public Page<ApplicationUserResponseDTO> searchApplicationUser(@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "true") boolean isActive, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "asc") String sortOrder) {
		return applicationUserService.searchApplicationUser(search, isActive, page, size, sortOrder);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteApplicationUser/{id}")
	public ApplicationUserResponseDTO softDeleteUser(@PathVariable String id) {
		return applicationUserService.softDeleteUser(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllApplicationUsers")
	public Page<ApplicationUserResponseDTO> getAllApplicationUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "asc") String sortOrder,
			@RequestParam(defaultValue = "true") boolean isActive) {
		return applicationUserService.getAllApplicationUsers(page, size, sortOrder, isActive);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateApplicationUser/{id}")
	public ApplicationUserResponseDTO updateUser(@RequestBody @Valid UpdateApplicationUserRequestDTO request,@PathVariable String id) {
		return applicationUserService.updateUser(request, id);
	}
	

}
