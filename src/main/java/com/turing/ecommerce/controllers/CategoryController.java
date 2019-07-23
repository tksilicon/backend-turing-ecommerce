package com.turing.ecommerce.controllers;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.CategoryAllDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.DTO.CategoryProductDTO;
import com.turing.ecommerce.model.Category;
import com.turing.ecommerce.model.ProductCategory;

import com.turing.ecommerce.service.CategoryService;
import com.turing.ecommerce.service.ProdCatDAOServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * Category Controller for all Rest APIs endpoints related to Category.
 * 
 * @author ThankGod Ukachukwu
 *
 */

@RestController
public class CategoryController {

	@Resource(name = "categoryImplService")
	private CategoryService categoryService;
	
	@Resource(name = "prodCatDAOImplService")
	private ProdCatDAOServiceImpl categoryService2;

	/*
	 * API to return all Categories
	 */
	@GetMapping(path = "/api/categories")
	public ResponseEntity<List<CategoryAllDTO>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}
	
	/*
	 * API to return a Category by id
	 */
	@GetMapping(path = "/api/categories/{category_id}")
	public ResponseEntity<Optional<Category>> getById(
			@PathVariable(name = "category_id", required = true) Integer categoryId) {
		return ResponseEntity.ok(categoryService.findById(categoryId));
	}

	/*
	 * API to return categories of product id
	 */
	@GetMapping(path = "/api/categories/inDepartment/{department_id}")
	public ResponseEntity<List<CategoryDTO>> getCategoryByDepartmentId(
			@PathVariable(name = "department_id", required = true) Integer departmentId) {

		return ResponseEntity.ok(categoryService.getCategoryByDepartmentId(departmentId));

	}
	
	/*
	 * API to return categories of department
	 */
	@GetMapping(path = "/api/categories/inProduct/{product_id}")
	public ResponseEntity<CategoryProductDTO> getCategoryByProductId(
			@PathVariable(name = "product_id", required = true) Integer productId) {

		return ResponseEntity.ok(categoryService2.findByProductId(productId));

	}

}
