package com.kinthrahub.backend.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kinthrahub.backend.dto.response.DonationPlanResponseDTO;
import com.kinthrahub.backend.entity.DonationPlan;
import com.kinthrahub.backend.exception.ResourceNotFoundException;
import com.kinthrahub.backend.mapper.DonationPlanMapper;
import com.kinthrahub.backend.repository.DonationPlanRepository;
import com.kinthrahub.backend.service.DonationPlanService;
import com.kinthrahub.backend.specification.DonationPlanSpecification;

@Service
public class DonationPlanServiceImpl implements DonationPlanService {

	private DonationPlanRepository donationPlanRepository;
	private DonationPlanMapper donationPlanMapper;

	public DonationPlanServiceImpl(DonationPlanRepository donationPlanRepository,
			DonationPlanMapper donationPlanMapper) {
		this.donationPlanMapper = donationPlanMapper;
		this.donationPlanRepository = donationPlanRepository;

	}

	public Page<DonationPlanResponseDTO> getAllDonationPlan(int page, int size, boolean isActive,String sortOrder) {

		Specification<DonationPlan> specification = Specification.where(DonationPlanSpecification.isActive(isActive));
		Sort sort;
		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "donationName");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "donationName");
		}
		Page<DonationPlan> donationPlans = donationPlanRepository.findAll(specification, PageRequest.of(page, size, sort));
		return donationPlans.map(donationPlanMapper::toResponseDTO);
		
	}

	public DonationPlanResponseDTO getDonationPlanById(String id) {
		DonationPlan donationPlan = donationPlanRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Donation plan not found for id : " + id));
		return donationPlanMapper.toResponseDTO(donationPlan);
	}

	public List<DonationPlanResponseDTO> getDonationPlansByHospital(String hospitalId, boolean isActive) {
		List<DonationPlan> donationPlans = donationPlanRepository.findByHospitalHospitalIdAndIsActive(hospitalId, true);

		return donationPlans.stream().map(donationPlanMapper::toResponseDTO).toList();
	}

	public Page<DonationPlanResponseDTO> searchDonationPlan(String search, boolean isActive, int page, int size,
			String sortOrder) {
		Specification<DonationPlan> specification = Specification.where(DonationPlanSpecification.search(search))
				.and(DonationPlanSpecification.isActive(isActive));

		Sort sort;

		if ("desc".equalsIgnoreCase(sortOrder)) {
			sort = Sort.by(Sort.Direction.DESC, "donationName");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "donationName");
		}

		Page<DonationPlan> donationPlans = donationPlanRepository.findAll(specification,
				PageRequest.of(page, size, sort));
		return donationPlans.map(donationPlanMapper::toResponseDTO);
	}

}
