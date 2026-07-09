/**
 * Response returned after a payroll run is executed.
 * Contains payroll execution details along with a summary
 * of processed requests and total donation amount.
 */

package com.carebridge.carebridge_backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.carebridge.carebridge_backend.enums.PayrollRunStatus;

import lombok.Data;

@Data
public class PayrollRunResponseDTO {

	private String payrollRunId;

	private Integer payrollMonth;

	private Integer payrollYear;

	private PayrollRunStatus runStatus;

//  Number of donation requests successfully processed during this payroll run
	private Integer processedRequests;

//  Number of donation requests skipped due to business rules
//  (e.g., cancelled, already processed, expired, etc.)
	private Integer skippedRequests;

//  Total amount deducted from all successfully processed donation requests
	private BigDecimal totalDonationAmount;

	private LocalDateTime processedOn;
	
	private String remarks;

}