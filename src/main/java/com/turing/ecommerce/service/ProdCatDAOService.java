package com.turing.ecommerce.service;

import com.turing.ecommerce.DTO.CategoryProductDTO;


public interface ProdCatDAOService {

	CategoryProductDTO findByProductId(int id);
}
