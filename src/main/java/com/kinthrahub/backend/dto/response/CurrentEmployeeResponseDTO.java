package com.kinthrahub.backend.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CurrentEmployeeResponseDTO {
	private String employeeId;
	private BigDecimal basicSalary;

}
