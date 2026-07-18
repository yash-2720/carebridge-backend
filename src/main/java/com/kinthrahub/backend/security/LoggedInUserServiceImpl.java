package com.kinthrahub.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.entity.ApplicationUser;
import com.kinthrahub.backend.entity.Employee;
import com.kinthrahub.backend.enums.RoleType;
import com.kinthrahub.backend.exception.ResourceNotFoundException;
import com.kinthrahub.backend.repository.ApplicationUserRepository;

@Service
public class LoggedInUserServiceImpl implements LoggedInUserService {

	private ApplicationUserRepository applicationUserRepository;

	public LoggedInUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public ApplicationUser getCurrentApplicationUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		ApplicationUser appUser = applicationUserRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException("Application user does not exists for username : " + username));
		return appUser;
	}

	@Override
	public Employee getCurrentEmployee() {
		Employee employee = getCurrentApplicationUser().getEmployee();
		return employee;
	}

	@Override
	public boolean isAdmin() {
		ApplicationUser appUser = getCurrentApplicationUser();
		return appUser.getRole().getRoleName().equals(RoleType.ADMIN.name());
	}

	@Override
	public boolean isPayrollAdmin() {
		ApplicationUser appUser = getCurrentApplicationUser();
		return appUser.getRole().getRoleName().equals(RoleType.PAYROLL_ADMIN.name());
	}

}
