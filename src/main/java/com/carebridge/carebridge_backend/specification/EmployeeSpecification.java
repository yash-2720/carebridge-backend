package com.carebridge.carebridge_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.carebridge.carebridge_backend.entity.Employee;

public class EmployeeSpecification {

	public static Specification<Employee> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeNumber")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeEmail")), pattern));
		};

	}

	public static Specification<Employee> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}

}
