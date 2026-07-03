package com.carebridge.carebridge_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.carebridge.carebridge_backend.entity.ApplicationUser;
import com.carebridge.carebridge_backend.entity.Employee;

//private String userId;
//private String employeeId;
//private String employeeName;	
//private String roleId;
//private String roleName;
//private String username;


public class ApplicationUserSpecification {
	public static Specification<ApplicationUser> search(String search) {

		return (root, query, criteriaBuilder) -> {

			if (search == null || search.isBlank()) {
				return criteriaBuilder.conjunction();
			}

			String pattern = "%" + search.toLowerCase() + "%";

			return criteriaBuilder.or(

					criteriaBuilder.like(criteriaBuilder.lower(root.get("userId")), pattern),
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("employeeId")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("employee").get("employeeName")), pattern),
					
					criteriaBuilder.like(criteriaBuilder.lower(root.get("role").get("roleName")), pattern),

					criteriaBuilder.like(criteriaBuilder.lower(root.get("role").get("roleId")), pattern));
		};

	}

	public static Specification<ApplicationUser> isActive(boolean isActive) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);

	}
}
