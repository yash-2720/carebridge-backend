package com.carebridge.carebridge_backend.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String>, JpaSpecificationExecutor<Hospital> {
	Page<Hospital> findAllByIsActive(boolean isActive, Pageable Page);

}
