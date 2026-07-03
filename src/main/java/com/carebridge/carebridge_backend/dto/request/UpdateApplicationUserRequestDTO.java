package com.carebridge.carebridge_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateApplicationUserRequestDTO {
	
	@NotBlank
    private String roleId;
}
