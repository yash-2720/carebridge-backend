package com.kinthrahub.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "donation_plan_table")
public class DonationPlan extends BaseEntity {


	@Id
	@Column(name = "donation_plan_id", nullable = false)
	private String donationPlanId;
		
	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private Hospital hospital;
	
	@Column(name = "donation_name", nullable = false)
	private String donationName;
	
	@Column(name = "donation_description", nullable = false)
	private String donationDescription;
	
}
