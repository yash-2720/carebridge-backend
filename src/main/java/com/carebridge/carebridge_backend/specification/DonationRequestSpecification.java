package com.carebridge.carebridge_backend.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.entity.DonationRequest;
import com.carebridge.carebridge_backend.enums.DonationStatus;
import com.carebridge.carebridge_backend.enums.DonationType;

//private String donationRequestId;
//
//private String employeeId;
//private String employeeName;
//
//private String donationPlanId;
//private String donationName;
//
//private DonationType donationType;
//
//private BigDecimal donationAmount;
//
//private LocalDate donationStartDate;
//
//private LocalDate donationEndDate;
//
//private DonationStatus donationStatus;
//
//private boolean isActive;
public class DonationRequestSpecification {
	public static Specification<DonationRequest> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequestId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("employeeId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("employeeName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationPlan").get("donationName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationPlan").get("donationPlanId")),
							pattern));
		};

	}

	public static Specification<DonationRequest> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}
}
