package com.carebridge.carebridge_backend.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Employee Number is required")
    private String employeeNumber;

    @NotBlank(message = "Employee Name is required")
    private String employeeName;

    @NotBlank(message = "Employee Email is required")
    @Email(message = "Invalid email format")
    private String employeeEmail;

    @NotBlank(message = "Employee Phone Number is required")
    @Pattern(
        regexp = "^\\+?[0-9]{10,15}$",
        message = "Invalid phone number"
    )
    private String employeePhoneNumber;

    @NotNull(message = "Basic Salary is required")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Salary must be greater than zero"
    )
    private BigDecimal basicSalary;

}