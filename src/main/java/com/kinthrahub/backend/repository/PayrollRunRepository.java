package com.kinthrahub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.PayrollRun;

@Repository
public interface PayrollRunRepository extends JpaRepository<PayrollRun, String>,  JpaSpecificationExecutor<PayrollRun> {
	boolean existsByPayrollMonthAndPayrollYear(Integer payrollMonth, Integer payrollYear);
}
