package com.kinthrahub.backend.repository;

import java.math.BigDecimal;

public interface MyDonationSummaryProjection {
	
	long getActiveDonations();

	BigDecimal getTotalDonationAmount();
}
