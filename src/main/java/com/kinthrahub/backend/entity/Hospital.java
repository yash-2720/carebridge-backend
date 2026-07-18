package com.kinthrahub.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hospital_table")
public class Hospital extends BaseEntity{


//		hospital_id varchar(20) PK 
//		hospital_name varchar(150) 
//		hospital_description varchar(500) 
//		is_active tinyint(1) 
//		created_by varchar(50) 
//		created_on timestamp 
//		modified_by varchar(50) 
//		modified_on timestamp
	
	@Id
	@Column(name = "hospital_id", nullable = false)
	private String hospitalId;
	
	
	@Column(name = "hospital_name", nullable = false)
	private String hospitalName;
	
	@Column(name = "hospital_description", nullable = false)
	private String hospitalDescription;
	
}
