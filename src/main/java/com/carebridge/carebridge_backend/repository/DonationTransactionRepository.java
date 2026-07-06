package com.carebridge.carebridge_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.DonationTransaction;

@Repository
public interface DonationTransactionRepository extends JpaRepository<DonationTransaction,String>, JpaSpecificationExecutor<DonationTransaction>{

//	Page<DonationTransaction> findAllById(String donationRequestId, PageRequest pageRequest);

	Page<DonationTransaction> findByDonationRequestDonationRequestId(String donationRequestId, Pageable pageable);
	
	

}
