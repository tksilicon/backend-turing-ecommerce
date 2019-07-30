package com.turing.ecommerce.service;

import com.turing.ecommerce.DTO.CategoryBasic;


public interface ProdCatDAOService {

	CategoryBasic findByProductId(int id);
}
