package com.turing.ecommerce.service;

import java.util.List;

import com.turing.ecommerce.DTO.AttributesProductDTO;



public interface AttributeDAOService {
	
	
    
	List<AttributesProductDTO> findAttributesByProductId(Integer productId);

}
