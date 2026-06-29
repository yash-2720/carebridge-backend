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
	
//	Columns:
//		employee_id varchar(20) PK 
//		employee_number varchar(20) 
//		employee_name varchar(150) 
//		employee_email varchar(150) 
//		employee_phone_number varchar(15) 
//		basic_salary decimal(10,2) 
//		is_active tinyint(1) 
//		created_by varchar(50) 
//		created_on timestamp 
//		modified_by varchar(50) 
//		modified_on timestamp
//	
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
