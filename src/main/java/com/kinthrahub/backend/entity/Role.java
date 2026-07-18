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
@Table(name = "role_table")
public class Role extends BaseEntity {
	
	@Id
	@Column(name = "role_id", nullable = false)
	private String roleId;
	
	@Column(name = "role_name", nullable = false)
	private String roleName;
	

}
