package com.kinthrahub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String>, JpaSpecificationExecutor<Employee> {
	
	List<Employee> findAllByIsActive(boolean isActive);
	
//	Employee findByEmployeeNumber(String employeeNumber);
	
	boolean existsByEmployeeNumber(String employeeNumber);
	
	boolean existsByEmployeeEmail(String employeeEmail);
	
	boolean existsByEmployeePhoneNumber(String employeePhoneNumber);

}
