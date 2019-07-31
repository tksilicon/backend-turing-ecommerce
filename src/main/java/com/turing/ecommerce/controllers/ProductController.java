package com.turing.ecommerce.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.ProductGetAllDTO;
import com.turing.ecommerce.DTO.ProductLocations;
import com.turing.ecommerce.DTO.ProductReviewDTO;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.DTO.Unauthorized;
import com.turing.ecommerce.exceptions.AuthenticatedUserException;
import com.turing.ecommerce.exceptions.ProductsGetProductsException;
import com.turing.ecommerce.exceptions.error;
import com.turing.ecommerce.model.Attribute;
import com.turing.ecommerce.model.Product;
import com.turing.ecommerce.model.Review;
import com.turing.ecommerce.service.ProductService;
import com.turing.ecommerce.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Product Controller for all Rest APIs endpoints related to Products.
 * 
 * @author thankGodukachukwu
 * 
 */
@Api(value = "Everything About Products")
@RestController
public class ProductController {

	@Resource(name = "productImplService")
	private ProductService productService;

	
	@Resource(name = "reviewImplService")
	private ReviewService reviewService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	/*
	 * API to return all products
	 */
	@ApiOperation(value = "Get All Products", response = Map.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the total of products and a list of Products in row.", response = ProductGetAllDTO.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products")
	public ResponseEntity<Map<String, Object>> getAll(HttpServletRequest request,
			@RequestParam(name = "page", required = false, defaultValue = "1") @Min(1) Integer page,
			@RequestParam(name = "limit", required = false, defaultValue ="20") Integer limit,
			@RequestParam(name = "description_length", required = false, defaultValue = "200") Integer description_length)
			throws ProductsGetProductsException {

		Map<String, Object> products = null;

		try {
			products = productService.getAll(page, limit, description_length);

		} catch (RuntimeException ex) {

			Map<String, String[]> parameters = request.getParameterMap();

			List<String> arr = new LinkedList<String>();

			List<Object> fields = new LinkedList<Object>();

			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
				String key = entry.getKey();

				String str = entry.getValue()[0].trim();

				if (key != null && str.trim().length() <= 0) {

					arr.add(entry.getKey());
					fields.add(entry.getKey());

				}

			}

			throw new ProductsGetProductsException(getErrorMessage(arr), fields);

		}

		return ResponseEntity.ok(products);
	}

	/*
	 * API to search all products
	 */
	@ApiOperation(value = "Search Products", response = Map.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the total of products and a list of Products in row.", response = ProductGetAllDTO.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	

	@GetMapping(path = "/api/products/search")
	public ResponseEntity<Map<String, Object>> productSearchQueryAllWords(
			@RequestParam(name = "query_string", required = true) String query_string,
			@RequestParam(name = "all_words", required = false, defaultValue = "on") String all_words,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(name = "description_length", required = false, defaultValue = "200") Integer description_length) {

		if (all_words.isEmpty() || all_words == null) {
			return ResponseEntity.ok(productService.productSearch(query_string, null, page, limit, description_length));
		} else {
			return ResponseEntity
					.ok(productService.productSearch(query_string, all_words, page, limit, description_length));
		}

	}

	/*
	 * API to return a Category by id
	 */
	@ApiOperation(value = "Product by ID", response = Product.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a Product Object.", response = Product.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/{product_id}")
	public ResponseEntity<Optional<Product>> getById(
			@PathVariable(name = "product_id", required = true) Integer productId) {
		return ResponseEntity.ok(productService.findById(productId));
	}

	/*
	 * API to search all products
	 */
	@ApiOperation(value = "Get a list of Products of Categories", response = Map.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list of Product Objects.", response = ProductGetAllDTO.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/inCategory/{category_id}")
	public ResponseEntity<Map<String, Object>> productOfCategories(
			@PathVariable(name = "category_id", required = true) Integer category_id,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(name = "description_length", required = false, defaultValue = "200") Integer description_length) {

		return ResponseEntity.ok(productService.productCategorySearch(category_id, page, limit, description_length));

	}

	/*
	 * API to search all products by department
	 */
	@ApiOperation(value = "Get a list of Products on Departments", response = Map.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the total and a list of products", response = ProductGetAllDTO.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/inDepartment/{department_id}")
	public ResponseEntity<Map<String, Object>> productOfDepartment(
			@PathVariable(name = "department_id", required = true) Integer department_id,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(name = "description_length", required = false, defaultValue = "200") Integer description_length) {

		return ResponseEntity
				.ok(productService.productDepartmentSearch(department_id, page, limit, description_length));

	}

	/*
	 * API to return product details
	 */
	@ApiOperation(value = "Get details of a Product", response = ProductDetail.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the total and a list of products", response = ProductDetail.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/{product_id}/details")
	public ResponseEntity<Optional<ProductDetail>> getDetailsProduct(
			@PathVariable(name = "product_id", required = true) Integer productId) {
		return ResponseEntity.ok(productService.getProductDetails(productId));
	}

	/*
	 * API to return product locations
	 */
	@ApiOperation(value = "Get locations of Product", response = ProductLocations.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return locations of products.", response = ProductLocations.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/{product_id}/locations")
	public ResponseEntity<Optional<ProductLocations>> getLocationsDetails(
			@PathVariable(name = "product_id", required = true) Integer productId) {
		return ResponseEntity.ok(productService.getProductLocations(productId));
	}

	/*
	 * API to return product reviews
	 */
	
	@ApiOperation(value = "Get reviews of a Product", response = ReviewDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list of reviews", response = ReviewDTO.class ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	
	@GetMapping(path = "/api/products/{product_id}/reviews")
	public ResponseEntity<List<ReviewDTO>> getProductReviews(
			@PathVariable(name = "product_id", required = true) Integer productId) {
		return ResponseEntity.ok(productService.getProductReviews(productId));
	}

	/*
	 * API to return post reviews
	 */
	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "No data" ),
            @ApiResponse(code = 400, message = "Return a error object", response = error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Unauthorized.class)})

	@PostMapping(path = "/api/products/{product_id}/reviews")
	public ResponseEntity<Review> postProductReviews( @Valid @RequestBody Review review) throws AuthenticatedUserException {

		return  ResponseEntity.ok(reviewService.save(review));
	}

	/**
	 * Helper method to generate error massage for missing and empty params
	 * @param arr
	 * @return
	 */
	String getErrorMessage(List<String> arr) {
		String output = "";
		for (int i = 0; i < arr.size(); i++)
			output += "The field " + arr.get(i) + " is empty.,";

		return output;
	}

}
