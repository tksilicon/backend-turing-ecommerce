package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.model.Attribute;
import com.turing.ecommerce.repository.AttributesRepository;

/**
 * AttributeServiceImpl for attribute related database access handling.
 * @author thankgodukachukwu
 *
 */
@Service("attributeImplService")
public class AttributesServiceImpl implements AttributesService {

	@Autowired
	private AttributesRepository attributesRepository;
	
	

	@Override
	public Optional<AttributeDTO> findById(Integer attributeId) {
		
		return attributesRepository.findAttribute(attributeId);
	}

	@Override
	public List<AttributeDTO> getAll() {
		
		return attributesRepository.findAllAttributes();
	}


	@Override
	public List<AttributeDTO> getValuesByAttributeId(Integer attributeId) {
		
		return attributesRepository.getValuesByAttributeId(attributeId);
	}

}
