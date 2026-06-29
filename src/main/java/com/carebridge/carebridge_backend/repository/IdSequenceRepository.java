package com.carebridge.carebridge_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carebridge.carebridge_backend.entity.IdSequence;

@Repository
public interface IdSequenceRepository extends JpaRepository<IdSequence, String>{

}
