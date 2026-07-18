package com.kinthrahub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinthrahub.backend.dto.response.DonationTransactionResponseDTO;
import com.kinthrahub.backend.service.DonationTransactionService;

@RestController
@RequestMapping("/donationTransaction")
public class DonationTransactionController {
	
	
	private DonationTransactionService donationTransactionService;
	
	public DonationTransactionController(DonationTransactionService donationTransactionService) {
		this.donationTransactionService = donationTransactionService;
	}
	

	@GetMapping("/getTransactionById/{id}")
	public DonationTransactionResponseDTO getTransactionById(@PathVariable String id) {
		return donationTransactionService.getTransactionById(id);
	}
	
	@GetMapping("/getAllTransactions")
	public Page<DonationTransactionResponseDTO> getAllDonationTransactions(@RequestParam (defaultValue = "0")int page,@RequestParam (defaultValue = "5") int size, @RequestParam (defaultValue = "asc")String sortOrder){
		return donationTransactionService.getAllDonationTransactions(page, size, sortOrder);
	}
	
	@GetMapping("/getTransactionByDonationRequestId/{id}")
	public Page<DonationTransactionResponseDTO> getTransactionsByDonationRequest(@PathVariable String id, @RequestParam (defaultValue = "0")int page,@RequestParam (defaultValue = "5") int size){
		return donationTransactionService.getTransactionsByDonationRequest(id, page, size);
	}
	
	@GetMapping("/search")
	public Page<DonationTransactionResponseDTO> SearchDonationTransactions(@RequestParam(required = false) String search,@RequestParam (defaultValue = "0")  int page,@RequestParam (defaultValue = "5")  int size,
			@RequestParam(defaultValue = "true")boolean isActive){
		return donationTransactionService.searchDonationTransactions(search, page, size, isActive);
		
	}
	
	@GetMapping("/getTransactionByPayrollId/{id}")
	public Page<DonationTransactionResponseDTO> getTransactionsByPayrollRunId(@PathVariable String id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		
		return donationTransactionService.getTransactionsByPayrollRunId(id, page, size);
		
	}

}
