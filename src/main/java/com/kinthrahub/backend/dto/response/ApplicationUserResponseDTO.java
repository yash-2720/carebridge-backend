package com.kinthrahub.backend.dto.response;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class ApplicationUserResponseDTO {
	private String userId;
	private String employeeId;
	private String employeeName;	
	private String roleId;
	private String roleName;
	private String username;
	private LocalDateTime lastLogin;
	private boolean isActive;
	
}
