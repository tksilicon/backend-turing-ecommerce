package com.turing.ecommerce.repository;

import com.turing.ecommerce.DTO.CategoryProductDTO;


public interface ProdCatDAO {

	CategoryProductDTO findByProductId(int id);
}
