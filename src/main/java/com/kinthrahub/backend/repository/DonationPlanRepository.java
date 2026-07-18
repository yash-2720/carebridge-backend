package com.kinthrahub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.DonationPlan;

@Repository
public interface DonationPlanRepository extends JpaRepository<DonationPlan, String>, JpaSpecificationExecutor<DonationPlan> {
	
	List<DonationPlan> findByHospitalHospitalIdAndIsActive(String hospitalId, boolean isActive);

}
