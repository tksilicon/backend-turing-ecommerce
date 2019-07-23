package com.turing.ecommerce.repository;

import java.util.List;

import com.turing.ecommerce.DTO.AttributesProductDTO;


public interface ProdAttributeDAO {
	
    List<AttributesProductDTO> findByProductId(int id);

}
