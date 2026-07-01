package com.carebridge.carebridge_backend.mapper;

import org.springframework.stereotype.Component;

import com.carebridge.carebridge_backend.dto.response.HospitalResponseDTO;
import com.carebridge.carebridge_backend.entity.Hospital;

@Component
public class HospitalMapper {

	public HospitalResponseDTO toResponseDTO(Hospital hospital) {

		HospitalResponseDTO response = new HospitalResponseDTO();
		response.setHospitalId(hospital.getHospitalId());
		response.setHospitalName(hospital.getHospitalName());
		response.setHospitalDescription(hospital.getHospitalDescription());
		response.setActive(hospital.isActive());

		return response;
	}

}
