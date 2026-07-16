package com.carebridge.carebridge_backend.security;

import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.entity.Employee;

public interface LoggedInUserService {
	
	ApplicationUser getCurrentApplicationUser();
	
	Employee getCurrentEmployee();
	
	boolean isAdmin();
	
	boolean isPayrollAdmin();
}
