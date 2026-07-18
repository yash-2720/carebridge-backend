package com.kinthrahub.backend.mapper;

import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.response.DonationTransactionResponseDTO;
import com.kinthrahub.backend.entity.DonationPlan;
import com.kinthrahub.backend.entity.DonationRequest;
import com.kinthrahub.backend.entity.DonationTransaction;
import com.kinthrahub.backend.entity.Employee;

@Component
public class DonationTransactionMapper {

	public DonationTransactionResponseDTO toResponseDTO(DonationTransaction donationTransaction) {

		DonationRequest donationRequest = donationTransaction.getDonationRequest();
		Employee employee = donationRequest.getEmployee();
		DonationPlan donationPlan = donationRequest.getDonationPlan();

		DonationTransactionResponseDTO response = new DonationTransactionResponseDTO();

		response.setDonationTransactionId(donationTransaction.getDonationTransactionId());

		response.setDonationRequestId(donationRequest.getDonationRequestId());

		response.setEmployeeId(employee.getEmployeeId());
		response.setEmployeeName(employee.getEmployeeName());

		response.setDonationPlanId(donationPlan.getDonationPlanId());
		response.setDonationPlanName(donationPlan.getDonationName());

		response.setPayrollPeriod(donationTransaction.getPayrollPeriod());
		response.setDeductedAmount(donationTransaction.getDeductedAmount());
		response.setTransactionStatus(donationTransaction.getTransactionStatus());
		response.setProcessedOn(donationTransaction.getProcessedOn());
		response.setRemarks(donationTransaction.getRemarks());

		response.setPayrollRunId(donationTransaction.getPayrollRun().getPayrollRunId());
		response.setActive(donationTransaction.isActive());

		return response;
	}
}
