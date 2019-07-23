package com.turing.ecommerce.service;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.turing.ecommerce.DTO.ProductDetailsDTO;
import com.turing.ecommerce.DTO.ProductLocationsDTO;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.model.Product;
import com.turing.ecommerce.model.Review;



/**
 * ProductService for product related  API handling.
 * 
 * @author thankgodukachukwu
 *
 */


public interface ProductService {
	

	Optional<Product> findById(Integer productId);
	Map<String, Object> getAll(Integer page, Integer limit, Integer description_length);
	
    Map<String, Object> productSearch(String query_string, String all_words, Integer page, Integer limit, Integer description_length);
    Map<String, Object> productSearch2(String query_string, String all_words, Integer page, Integer limit, Integer description_length);

	Map<String, Object> productCategorySearch(Integer category_id, Integer page, Integer limit, Integer description_length);
	Map<String, Object> productDepartmentSearch(Integer department_id, Integer page, Integer limit, Integer description_length);


	Optional<ProductDetailsDTO> getProductDetails(Integer productId);
    Optional<ProductLocationsDTO> getProductLocations(Integer productId);
	
    List<ReviewDTO> getProductReviews(Integer productId);
	
   
	
	
		
	

}
