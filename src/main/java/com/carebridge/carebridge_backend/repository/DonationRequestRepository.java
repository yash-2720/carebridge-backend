package com.carebridge.carebridge_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.DonationRequest;

@Repository
public interface DonationRequestRepository extends JpaRepository<DonationRequest,String>, JpaSpecificationExecutor<DonationRequest> {
	
	boolean existsByEmployeeEmployeeId(String employeeId);
}
