package com.turing.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.CategoryProductDTO;
import com.turing.ecommerce.repository.ProdCategoryRepository;

@Service("prodCatDAOImplService")
public class ProdCatDAOServiceImpl implements ProdCatDAOService {

	
	@Autowired
	ProdCategoryRepository prodCategoryRepository;
	
	@Override
	public CategoryProductDTO findByProductId(int id) {
		
		return prodCategoryRepository.findByProductId(id);
		
		
	}

}
