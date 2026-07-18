package com.kinthrahub.backend.sequence;

import org.springframework.stereotype.Service;

import com.kinthrahub.backend.entity.IdSequence;
import com.kinthrahub.backend.repository.IdSequenceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SequenceGeneratorImpl implements SequenceGenerator {
	
	private IdSequenceRepository idSequenceRepository;
	
	public SequenceGeneratorImpl(IdSequenceRepository idSequenceRepository) {
		this.idSequenceRepository = idSequenceRepository;
	}
	
//	String code = generateId("EMP");
	@Override
	public String generateId(String code) {
		// TODO Auto-generated method stub
		IdSequence idSequence =idSequenceRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Invalide code : "+ code));
		Long currentValue = idSequence.getCurrentValue() + 1;

		idSequence.setCurrentValue(currentValue);
		idSequenceRepository.save(idSequence);
		String formattedNumber = String.format("%08d", currentValue);

		String generatedCode = idSequence.getCode() + formattedNumber; 
		System.out.println(generatedCode);
		return generatedCode;
		
	}



}

	