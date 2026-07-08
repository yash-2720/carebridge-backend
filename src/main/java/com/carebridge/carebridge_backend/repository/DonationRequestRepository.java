package com.carebridge.carebridge_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.DonationRequest;
import com.carebridge.carebridge_backend.enums.DonationStatus;

@Repository
public interface DonationRequestRepository
		extends JpaRepository<DonationRequest, String>, JpaSpecificationExecutor<DonationRequest> {

	boolean existsByEmployeeEmployeeId(String employeeId);

	List<DonationRequest> findByDonationStatusAndIsActive(DonationStatus donationStatus, boolean isActive);
}
