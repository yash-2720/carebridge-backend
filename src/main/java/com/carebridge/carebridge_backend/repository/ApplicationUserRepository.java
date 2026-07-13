package com.carebridge.carebridge_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository
		extends JpaRepository<ApplicationUser, String>, JpaSpecificationExecutor<ApplicationUser> {
	boolean existsByUsername(String username);

	boolean existsByEmployeeEmployeeId(String employeeId);
	
	Optional<ApplicationUser> findByUsername(String username);

}
