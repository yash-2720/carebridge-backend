package com.kinthrahub.backend.security;

import com.kinthrahub.backend.entity.ApplicationUser;
import com.kinthrahub.backend.entity.Employee;

public interface LoggedInUserService {
	
	ApplicationUser getCurrentApplicationUser();
	
	Employee getCurrentEmployee();
	
	boolean isAdmin();
	
	boolean isPayrollAdmin();
}
