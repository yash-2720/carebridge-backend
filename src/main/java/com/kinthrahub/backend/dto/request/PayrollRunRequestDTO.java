package com.kinthrahub.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PayrollRunRequestDTO {

    @NotNull(message = "Payroll month is required")
    @Min(value = 1, message = "Payroll month must be between 1 and 12")
    @Max(value = 12, message = "Payroll month must be between 1 and 12")
    private Integer payrollMonth;

    @NotNull(message = "Payroll year is required")
    @Min(value = 2000, message = "Payroll year must be greater than or equal to 2000")
    @Max(value = 9999, message = "Payroll year must be a valid 4-digit year")
    private Integer payrollYear;

}