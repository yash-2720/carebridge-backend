package com.carebridge.carebridge_backend.entity;

import java.time.LocalDateTime;

import com.carebridge.carebridge_backend.enums.PayrollRunStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payroll_run_table")
public class PayrollRun extends BaseEntity{


	@Id
	@Column(name = "payroll_run_id", nullable = false)
	private String payrollRunId;

	@Column(name = "payroll_month", nullable = false)
	private Integer payrollMonth;

	@Column(name = "payroll_year", nullable = false)
	private Integer payrollYear;

	@Enumerated(EnumType.STRING)
	@Column(name = "run_status", nullable = false)
	private PayrollRunStatus runStatus;

	@Column(name = "processed_on")
	private LocalDateTime processedOn;

	@Column(name = "processed_by", nullable = false)
	private String processedBy;

	@Column(name = "remarks")
	private String remarks;	
}
