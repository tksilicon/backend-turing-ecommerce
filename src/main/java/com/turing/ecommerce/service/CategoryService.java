package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.Department;
import com.turing.ecommerce.model.ProductCategory;


public interface CategoryService {

	

	Optional<Category> findById(Integer categoryId);

	
	public List<CategoryAllDTO> getAll();

	public List<CategoryDTO> getCategoryByDepartmentId(Integer departmentId);

	

}
