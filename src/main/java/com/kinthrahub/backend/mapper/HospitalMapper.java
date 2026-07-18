package com.kinthrahub.backend.mapper;

import org.springframework.stereotype.Component;

import com.kinthrahub.backend.dto.response.HospitalResponseDTO;
import com.kinthrahub.backend.entity.Hospital;

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
