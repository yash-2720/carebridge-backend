package com.kinthrahub.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.DonationRequest;
import com.kinthrahub.backend.entity.Employee;
import com.kinthrahub.backend.enums.DonationStatus;

@Repository
public interface DonationRequestRepository
		extends JpaRepository<DonationRequest, String>, JpaSpecificationExecutor<DonationRequest> {

	boolean existsByEmployeeEmployeeId(String employeeId);

	List<DonationRequest> findByDonationStatusAndIsActive(DonationStatus donationStatus, boolean isActive);
	
	Page<DonationRequest> findByEmployee(Employee employee, Pageable pageable);
	
	DonationRequest findByEmployee(Employee employee);
	
	Optional<DonationRequest> findByDonationRequestIdAndEmployeeEmployeeId(String donationRequestId, String employeeId );
}
