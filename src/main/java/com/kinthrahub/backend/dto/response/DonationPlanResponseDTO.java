package com.kinthrahub.backend.dto.response;

import lombok.Data;

@Data
public class DonationPlanResponseDTO {

	private String donationPlanId;

	private String donationName;

	private String donationDescription;

	private boolean isActive;

	private String hospitalId;

	private String hospitalName;

}
