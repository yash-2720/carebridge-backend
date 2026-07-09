package com.carebridge.carebridge_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.carebridge.carebridge_backend.entity.PayrollRun;

public class PayrollRunSpecification {
	public static Specification<PayrollRun> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("payrollRunId")), pattern),

					criteriaBuilder.like(root.get("payrollMonth").as(String.class), pattern),

					criteriaBuilder.like(root.get("payrollYear").as(String.class), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("runStatus").as(String.class)), pattern),
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("remarks")), pattern));
		};
	}

}
