package com.carebridge.carebridge_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee_table")
public class Employee extends BaseEntity {

	@Id
	@Column(name = "employee_id" , nullable = false)
	private String employeeId;
	
	@Column(name = "employee_number", nullable = false, unique = true)
	private String employeeNumber;
	

	@Column(name = "employee_name", nullable = false )
	private String employeeName;
	

	@Column(name = "employee_email", nullable = false, unique = true)
	private String employeeEmail;

	@Column(name = "employee_phone_number", nullable = false, unique = true)
	private String employeePhoneNumber;
	

	@Column(name = "basic_salary",  precision = 10, scale = 2, nullable = false )
	private BigDecimal basicSalary;
	
	

}
