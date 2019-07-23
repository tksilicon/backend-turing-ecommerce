package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.ProductCategory;
import com.turing.ecommerce.repository.CategoryRepository;

/**
 * CategoryServiceImpl for Category related database access handling.
 * @author ThankGod Ukachukwu
 *
 */
@Service("categoryImplService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public Optional<Category> findById(Integer categoryId) {
		
		return  categoryRepository.findById(categoryId);
	}

	@Override
	public List<CategoryAllDTO> getAll() {
		
		return categoryRepository.getAllOfCategory();
	}

	@Override
	public List<CategoryDTO> getCategoryByDepartmentId(Integer departmentId) {
		
		return categoryRepository.getCategoryByDepartmentId(departmentId);
	}


	
	

}
