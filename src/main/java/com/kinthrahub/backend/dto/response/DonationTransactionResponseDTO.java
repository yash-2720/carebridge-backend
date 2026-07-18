package com.kinthrahub.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kinthrahub.backend.enums.TransactionStatus;

import lombok.Data;

@Data
public class DonationTransactionResponseDTO {

	private String donationTransactionId;

	private String donationRequestId;

	private String employeeId;

	private String employeeName;

	private String donationPlanId;

	private String donationPlanName;

	private String payrollPeriod;

	private BigDecimal deductedAmount;

	private TransactionStatus transactionStatus;

	private LocalDateTime processedOn;

	private String remarks;

	private String payrollRunId;
	
	private boolean isActive;

}
