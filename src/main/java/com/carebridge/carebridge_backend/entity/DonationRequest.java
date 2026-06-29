package com.carebridge.carebridge_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.carebridge.carebridge_backend.enums.DonationStatus;
import com.carebridge.carebridge_backend.enums.DonationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "donation_request_table")
public class DonationRequest extends BaseEntity {

	@Id
	@Column(name = "donation_request_id", nullable = false)
	private String donationRequestId;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "donation_plan_id", nullable = false)
	private DonationPlan donationPlan;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "donation_type", nullable = false)
	private DonationType  donationType;
	
	@Column(name = "donation_amount",  precision = 10, scale = 2, nullable = false )
	private BigDecimal donationAmount;
	
	@Column(name = "donation_start_date", nullable = false)
	private LocalDate donationStartDate;
	
	@Column(name = "donation_end_date", nullable = true)
	private LocalDate donationEndDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "donation_status" ,nullable = false)
	private DonationStatus donationStatus;
}
