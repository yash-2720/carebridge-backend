package com.carebridge.carebridge_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carebridge.carebridge_backend.enums.TransactionStatus;

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
@Table(name = "donation_transaction_table")
public class DonationTransaction extends BaseEntity {
	
//	donation_transaction_id varchar(20) PK 
//	donation_request_id varchar(20) 
//	payroll_period varchar(20) 
//	deducted_amount decimal(10,2) 
//	transaction_status varchar(50) 
//	processed_on timestamp 
//	remarks varchar(100)
	
	@Id
	@Column(name = "donation_transaction_id", nullable = false)
	private String donationTransactionId;
	
	@ManyToOne
	@JoinColumn(name = "donation_request_id", nullable = false)
	private DonationRequest donationRequest;
	
	@Column(name = "payroll_period", nullable = false)
	private String payrollPeriod;
	
	@Column(name = "deducted_amount", precision = 10, scale = 2, nullable = false)
	private BigDecimal deductedAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status", nullable = false)
	private TransactionStatus transactionStatus;
	
	@Column(name = "remarks", nullable = true)
	private String remarks;
	
	@Column(name = "processed_on", nullable = true)
	private LocalDateTime processedOn;
	

}
