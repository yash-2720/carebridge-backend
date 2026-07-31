package com.kinthrahub.backend.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EmployeeResponseDTO {

    private String employeeId;
    private String employeeNumber;
    private String employeeName;
    private String employeeEmail;
    private String employeePhoneNumber;
    private BigDecimal basicSalary;
    private boolean isActive;
    private boolean applicationUserCreated;
}