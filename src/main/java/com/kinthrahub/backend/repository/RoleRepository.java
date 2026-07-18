package com.kinthrahub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

}
