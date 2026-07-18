package com.kinthrahub.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.kinthrahub.backend.entity.DonationRequest;
import com.kinthrahub.backend.entity.Employee;

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

	public static Specification<DonationRequest> employee(Employee employee) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("employee"), employee);
	}
}
