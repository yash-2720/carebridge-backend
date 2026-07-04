package com.carebridge.carebridge_backend.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.carebridge.carebridge_backend.enums.DonationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DonationRequestDTO {

    @NotBlank(message = "Employee Id is required")
    private String employeeId;

    @NotBlank(message = "Donation Plan Id is required")
    private String donationPlanId;

    @NotNull(message = "Donation Type is required")
    private DonationType donationType;

    @NotNull(message = "Donation Amount is required")
    private BigDecimal donationAmount;

    @NotNull(message = "Donation Start Date is required")
    private LocalDate donationStartDate;

    private LocalDate donationEndDate;
}