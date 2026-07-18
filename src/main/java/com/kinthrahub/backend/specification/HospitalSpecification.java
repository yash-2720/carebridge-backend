package com.kinthrahub.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.kinthrahub.backend.entity.Hospital;

public class HospitalSpecification {

	public static Specification<Hospital> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("hospitalId")), pattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("hospitalDescription")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("hospitalName")), pattern));
		};
//		criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeNumber")), pattern),
//		criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeEmail")), pattern));
	}

	public static Specification<Hospital> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}

}