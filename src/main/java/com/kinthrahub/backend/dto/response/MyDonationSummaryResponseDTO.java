package com.kinthrahub.backend.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MyDonationSummaryResponseDTO {

    private long activeDonations;

    private BigDecimal totalDonationAmount;
}