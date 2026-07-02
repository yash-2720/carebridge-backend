package com.carebridge.carebridge_backend.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.carebridge.carebridge_backend.dto.response.DonationPlanResponseDTO;
import com.carebridge.carebridge_backend.entity.DonationPlan;
import com.carebridge.carebridge_backend.exception.ResourceNotFoundException;
import com.carebridge.carebridge_backend.mapper.DonationPlanMapper;
import com.carebridge.carebridge_backend.repository.DonationPlanRepository;
import com.carebridge.carebridge_backend.service.DonationPlanService;
import com.carebridge.carebridge_backend.specification.DonationPlanSpecification;

@Service
public class DonationPlanServiceImpl implements DonationPlanService {

	private DonationPlanRepository donationPlanRepository;
	private DonationPlanMapper donationPlanMapper;

	public DonationPlanServiceImpl(DonationPlanRepository donationPlanRepository,
			DonationPlanMapper donationPlanMapper) {
		this.donationPlanMapper = donationPlanMapper;
		this.donationPlanRepository = donationPlanRepository;

	}

	public Page<DonationPlanResponseDTO> getAllDonationPlan(int page, int size, boolean isActive) {
		// TODO Auto-generated method stub

		Specification<DonationPlan> specification = Specification.where(DonationPlanSpecification.isActive(isActive));
		Page<DonationPlan> donationPlans = donationPlanRepository.findAll(specification, PageRequest.of(page, size));
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
