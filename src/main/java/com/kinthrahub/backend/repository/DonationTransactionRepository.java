package com.kinthrahub.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.DonationTransaction;

@Repository
public interface DonationTransactionRepository
		extends JpaRepository<DonationTransaction, String>, JpaSpecificationExecutor<DonationTransaction> {

//	Page<DonationTransaction> findAllById(String donationRequestId, PageRequest pageRequest);

	Page<DonationTransaction> findByDonationRequestDonationRequestId(String donationRequestId, Pageable pageable);
	
	Page<DonationTransaction> findByPayrollRunPayrollRunId( String payrollRunId, Pageable pageable);

	boolean existsByDonationRequestDonationRequestIdAndPayrollPeriod(String donationRequestId, String payrollPeriod);

	Optional<DonationTransaction> findByDonationTransactionIdAndDonationRequestEmployeeEmployeeId(String donationTransactionId, String employeeId);
}
