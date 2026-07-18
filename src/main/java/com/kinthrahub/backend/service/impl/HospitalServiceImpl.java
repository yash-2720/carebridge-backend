package com.kinthrahub.backend.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.response.HospitalResponseDTO;
import com.kinthrahub.backend.entity.Hospital;
import com.kinthrahub.backend.exception.ResourceNotFoundException;
import com.kinthrahub.backend.mapper.HospitalMapper;
import com.kinthrahub.backend.repository.HospitalRepository;
import com.kinthrahub.backend.service.HospitalService;
import com.kinthrahub.backend.specification.HospitalSpecification;

@Service
public class HospitalServiceImpl implements HospitalService {

	private HospitalRepository hospitalRepository;
	private HospitalMapper hospitalMapper;

	public HospitalServiceImpl(HospitalRepository hospitalRepository, HospitalMapper hospitalMapper) {
		this.hospitalRepository = hospitalRepository;
		this.hospitalMapper = hospitalMapper;
	}

	public Page<HospitalResponseDTO> getAllHospitals(boolean isActive, int page, int size, String sortOrder) {

		Sort sort;

		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "hospitalName");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "hospitalName");
		}
		Page<Hospital> hospitals = hospitalRepository.findAllByIsActive(isActive, PageRequest.of(page, size, sort));

		return hospitals.map(hospitalMapper::toResponseDTO);
	}

	public HospitalResponseDTO findHospitalById(String id) {
		Hospital hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital not found with Id : " + id));
		return hospitalMapper.toResponseDTO(hospital);
	}

	public Page<HospitalResponseDTO> searchHospital(String search, boolean isActive, int page, int size,
			String sortOrder) {
		Specification<Hospital> specification = Specification.where(HospitalSpecification.search(search))
				.and(HospitalSpecification.isActive(isActive));

		Sort sort;

		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "hospitalName");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "hospitalName");
		}

		Page<Hospital> hospitals = hospitalRepository.findAll(specification, PageRequest.of(page, size, sort));
		return hospitals.map(hospitalMapper::toResponseDTO);
	}

}
