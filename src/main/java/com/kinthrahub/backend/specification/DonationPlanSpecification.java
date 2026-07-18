package com.kinthrahub.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.kinthrahub.backend.entity.DonationPlan;
import com.kinthrahub.backend.entity.Hospital;

public class DonationPlanSpecification {
	
	public static Specification<DonationPlan> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationPlanId")), pattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationDescription")), pattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("donationName")), pattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("hospital").get("hospitalId")), pattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("hospital").get("hospitalName")), pattern));
		};
//		criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeNumber")), pattern),
//		criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeEmail")), pattern));
	}
	public static Specification<DonationPlan> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}


}
