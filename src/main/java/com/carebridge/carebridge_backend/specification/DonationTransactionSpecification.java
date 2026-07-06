package com.carebridge.carebridge_backend.specification;


import org.springframework.data.jpa.domain.Specification;

import com.carebridge.carebridge_backend.entity.DonationTransaction;




public class DonationTransactionSpecification {
	public static Specification<DonationTransaction> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationTransactionId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequest").get("donationRequestId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequest").get("employee").get("employeeId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequest").get("employee").get("employeeName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequest").get("donationPlan").get("donationName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationRequest").get("donationPlan").get("donationPlanId")), pattern),
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("payrollPeriod")), pattern),
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionStatus")),pattern));
		};
	}
	
	public static Specification<DonationTransaction> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}
}
