package com.kinthrahub.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.kinthrahub.backend.entity.IdSequence;

import jakarta.persistence.LockModeType;

@Repository
public interface IdSequenceRepository extends JpaRepository<IdSequence, String>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<IdSequence> findByCode(String code);

}
