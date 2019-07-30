package com.turing.ecommerce.repository;

import com.turing.ecommerce.DTO.CategoryBasic;


public interface ProdCatDAO {

	CategoryBasic findByProductId(int id);
}
