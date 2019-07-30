package com.turing.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.turing.ecommerce.DTO.ProductInDepartment;
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.ProductLocations;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.model.Product;
import com.turing.ecommerce.model.Review;
import com.turing.ecommerce.repository.ProductRepository;
import com.turing.ecommerce.repository.ReviewRepository;

/**
 * ProductServiceImpl for product related database access handling.
 * 
 * @author thankgodukachukwu
 *
 */

@Service("productImplService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Map<String, Object> getAll(Integer page, Integer limit, Integer description_length) {

		Pageable pageable = PageRequest.of(page - 1, limit);

		List<Product> products = productRepository.getAll(description_length, pageable);

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();
		mapOfReturn.put("count", products.size());
		mapOfReturn.put("rows", products);

		return mapOfReturn;

	}

	@Override
	public Map<String, Object> productSearch(String query_string, String all_words, Integer page, Integer limit,
			Integer description_length) {

		Pageable pageable = PageRequest.of(page - 1, limit);

		List<Product> products = productRepository.productSearch(query_string, description_length, pageable);
		

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();
		mapOfReturn.put("count", products.size());
		mapOfReturn.put("rows", products);

		return mapOfReturn;
	}
	
	@Override
	public Map<String, Object> productSearch2(String query_string, String all_words, Integer page, Integer limit,
			Integer description_length) {

		Pageable pageable = PageRequest.of(page - 1, limit);

		List<Product> products = productRepository.productSearch2(all_words, description_length, pageable);
		

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();
		mapOfReturn.put("count", products.size());
		mapOfReturn.put("rows", products);

		return mapOfReturn;
	}

	@Override
	public Optional<Product> findById(Integer productId) {
		
		return productRepository.findById(productId);
	}

	@Override
	public Map<String, Object> productCategorySearch(Integer category_id, Integer page, Integer limit,
			Integer description_length) {
		
		Pageable pageable = PageRequest.of(page - 1, limit);
		
		List<ProductInDepartment> products	= productRepository.productSearchCategory(category_id, description_length, pageable);
	

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();
		mapOfReturn.put("count", products.size());
		mapOfReturn.put("rows", products);

		return mapOfReturn;
	}

	@Override
	public Map<String, Object> productDepartmentSearch(Integer department_id, Integer page, Integer limit,
			Integer description_length) {
		
		Pageable pageable = PageRequest.of(page - 1, limit);
		
		List<ProductInDepartment> products	= productRepository.productSearchDepartment(department_id, description_length, pageable);
	

		Map<String, Object> mapOfReturn = new HashMap<String, Object>();
		mapOfReturn.put("count", products.size());
		mapOfReturn.put("rows", products);

		return mapOfReturn;
	}

	@Override
	public Optional<ProductDetail> getProductDetails(Integer productId) {
		
		
		return productRepository.getProductDetails(productId);
		
	}

	@Override
	public Optional<ProductLocations> getProductLocations(Integer productId) {
		
		return productRepository.getProductLocations(productId);
	}

	@Override
	public List<ReviewDTO> getProductReviews(Integer productId) {
		
		
		return productRepository.fetchProdReviews(productId);
	}

	
   
	
	
	
	
	

}
