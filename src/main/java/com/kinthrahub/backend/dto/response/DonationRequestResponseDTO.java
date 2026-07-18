package com.kinthrahub.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kinthrahub.backend.enums.DonationStatus;
import com.kinthrahub.backend.enums.DonationType;

import lombok.Data;

@Data
public class DonationRequestResponseDTO {

    private String donationRequestId;

    private String employeeId;
    private String employeeName;

    private String donationPlanId;
    private String donationName;

    private DonationType donationType;

    private BigDecimal donationAmount;

    private LocalDate donationStartDate;

    private LocalDate donationEndDate;

    private DonationStatus donationStatus;

    private boolean isActive;
}