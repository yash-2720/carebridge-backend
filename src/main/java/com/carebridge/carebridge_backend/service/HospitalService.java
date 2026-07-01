package com.carebridge.carebridge_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.carebridge.carebridge_backend.dto.response.HospitalResponseDTO;

public interface HospitalService {

	public Page<HospitalResponseDTO> getAllHospitals(boolean isActive, int page, int size, String sortOrder);

	public HospitalResponseDTO findHospitalById(String id);

	public Page<HospitalResponseDTO> searchHospital(String search, boolean isActive, int page, int size,
			String sortOrder);

}
