package com.carebridge.carebridge_backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.carebridge_backend.dto.response.HospitalResponseDTO;
import com.carebridge.carebridge_backend.service.HospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	private HospitalService hospitalService;

	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@GetMapping("/getAllHospitals")
	public Page<HospitalResponseDTO> getAllHospitals(@RequestParam(defaultValue = "true") boolean isActive,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "asc") String sortOrder) {

		return hospitalService.getAllHospitals(isActive, page, size, sortOrder);

	}

	@GetMapping("/getHospitalById/{id}")
	public HospitalResponseDTO getHospitalById(@PathVariable String id) {

		return hospitalService.findHospitalById(id);

	}

	@GetMapping("/search")
	public Page<HospitalResponseDTO> search(@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "true") boolean isActive, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "asc") String sortOrder) {
		return hospitalService.searchHospital(search, isActive, page, size, sortOrder);
	}

}
