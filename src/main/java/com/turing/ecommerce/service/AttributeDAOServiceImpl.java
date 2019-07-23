package com.turing.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.AttributesProductDTO;
import com.turing.ecommerce.repository.ProdAttributesRepository;


@Service("attributeDAOServiceImpl")
public class AttributeDAOServiceImpl implements AttributeDAOService {
	
	
	@Autowired
	ProdAttributesRepository  prodAttributesRepository;
	

	@Override
	public List<AttributesProductDTO> findAttributesByProductId(Integer productId) {
		return prodAttributesRepository.findByProductId(productId);
	} 
	
	
	

}
