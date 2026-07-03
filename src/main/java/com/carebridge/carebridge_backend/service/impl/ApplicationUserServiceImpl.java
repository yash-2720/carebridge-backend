package com.carebridge.carebridge_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.request.ApplicationUserRequestDTO;
import com.carebridge.carebridge_backend.dto.request.UpdateApplicationUserRequestDTO;
import com.carebridge.carebridge_backend.dto.response.ApplicationUserResponseDTO;
import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.entity.Employee;
import com.carebridge.carebridge_backend.entity.Role;
import com.carebridge.carebridge_backend.exception.ResourceAlreadyExistsException;
import com.carebridge.carebridge_backend.exception.ResourceNotFoundException;
import com.carebridge.carebridge_backend.mapper.ApplicationUserMapper;
import com.carebridge.carebridge_backend.repository.ApplicationUserRepository;
import com.carebridge.carebridge_backend.repository.EmployeeRepository;
import com.carebridge.carebridge_backend.repository.RoleRepository;
import com.carebridge.carebridge_backend.sequence.SequenceGenerator;
import com.carebridge.carebridge_backend.service.ApplicationUserService;
import com.carebridge.carebridge_backend.specification.ApplicationUserSpecification;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

	private final RoleRepository roleRepository;

	private ApplicationUserRepository applicationUserRepository;
	private SequenceGenerator sequenceGenerator;
	private ApplicationUserMapper applicationUserMapper;
	private EmployeeRepository employeeRepository;

	public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
			SequenceGenerator sequenceGenerator, ApplicationUserMapper applicationUserMapper,
			EmployeeRepository employeeRepository, RoleRepository roleRepository) {

		this.applicationUserMapper = applicationUserMapper;
		this.sequenceGenerator = sequenceGenerator;
		this.applicationUserRepository = applicationUserRepository;
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;

	}

	public ApplicationUserResponseDTO getAppUserById(String id) {
		ApplicationUser appUser = applicationUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Application User not found for Id : " + id));
		return applicationUserMapper.toResponseDTO(appUser);

	}

	public ApplicationUserResponseDTO addNewAppUser(ApplicationUserRequestDTO request) {

		ApplicationUser appUser = applicationUserMapper.toEntity(request);

		// Check if employee already has an application user
		if (applicationUserRepository.existsByEmployeeEmployeeId(request.getEmployeeId())) {
			throw new ResourceAlreadyExistsException(
					"Application User already exists for Employee Id : " + request.getEmployeeId());
		}

		// Check if username already exists
		if (applicationUserRepository.existsByUsername(request.getUsername())) {
			throw new ResourceAlreadyExistsException("Username already exists : " + request.getUsername());
		}

		Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee Not Found for Id :" + request.getEmployeeId()));

		Role role = roleRepository.findById(request.getRoleId())
				.orElseThrow(() -> new ResourceNotFoundException("Role Not found for Id :" + request.getRoleId()));
		appUser.setEmployee(employee);
		appUser.setRole(role);
		appUser.setUserId(sequenceGenerator.generateId("USR"));
		applicationUserRepository.save(appUser);

		return applicationUserMapper.toResponseDTO(appUser);

	}

	public Page<ApplicationUserResponseDTO> searchApplicationUser(String search, boolean isActive, int page, int size,
			String sortOrder) {
		Specification<ApplicationUser> specification = Specification.where(ApplicationUserSpecification.search(search))
				.and(ApplicationUserSpecification.isActive(isActive));

		Sort sort;

		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "username");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "username");
		}

		Page<ApplicationUser> applicationUsers = applicationUserRepository.findAll(specification,
				PageRequest.of(page, size, sort));
		return applicationUsers.map(applicationUserMapper::toResponseDTO);
	}

	public ApplicationUserResponseDTO softDeleteUser(String id) {
		ApplicationUser appUser = applicationUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Application User not found for Id" + id));
		appUser.setActive(false);
		applicationUserRepository.save(appUser);
		return applicationUserMapper.toResponseDTO(appUser);
	}

	public Page<ApplicationUserResponseDTO> getAllApplicationUsers(int page, int size, String sortOrder,
			boolean isActive) {
		Specification<ApplicationUser> specification = Specification
				.where(ApplicationUserSpecification.isActive(isActive));
		Sort sort;

		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "username");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "username");
		}
		Page<ApplicationUser> applicationUsers = applicationUserRepository.findAll(specification,
				PageRequest.of(page, size, sort));
		return applicationUsers.map(applicationUserMapper::toResponseDTO);
	}
	
	
	public ApplicationUserResponseDTO updateUser(UpdateApplicationUserRequestDTO request, String id) {
		ApplicationUser appUser = applicationUserRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Application User Not found for Id : "+id));

		Role role = roleRepository.findById(request.getRoleId())
				.orElseThrow(() -> new ResourceNotFoundException("Role Not found for Id :" + request.getRoleId()));
		appUser.setRole(role);
		applicationUserRepository.save(appUser);
		return applicationUserMapper.toResponseDTO(appUser);
	}

}
