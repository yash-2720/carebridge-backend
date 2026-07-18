package com.kinthrahub.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository
		extends JpaRepository<ApplicationUser, String>, JpaSpecificationExecutor<ApplicationUser> {
	boolean existsByUsername(String username);

	boolean existsByEmployeeEmployeeId(String employeeId);
	
	Optional<ApplicationUser> findByUsername(String username);

}
