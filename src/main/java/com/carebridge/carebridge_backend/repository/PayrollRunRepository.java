package com.carebridge.carebridge_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.PayrollRun;

@Repository
public interface PayrollRunRepository extends JpaRepository<PayrollRun, String> {

}
