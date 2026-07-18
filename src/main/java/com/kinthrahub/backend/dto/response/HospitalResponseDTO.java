package com.kinthrahub.backend.dto.response;

import lombok.Data;

@Data
public class HospitalResponseDTO {

	private String hospitalId;
	
	private String hospitalName;

	private String hospitalDescription;
	
	private boolean isActive;

}
