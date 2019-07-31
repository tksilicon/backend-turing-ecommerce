package com.turing.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.turing.ecommerce.DTO.ProductInDepartment;
import com.turing.ecommerce.DTO.ProductDetail;
import com.turing.ecommerce.DTO.ProductLocations;
import com.turing.ecommerce.DTO.ReviewDTO;
import com.turing.ecommerce.model.Product;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	@Query(value = "select p from Product p where p.description = SUBSTRING(p.description, 1,:description_length)")
	List<Product> getAll(@Param("description_length")Integer description_length, Pageable pageable);

	@Query(value = "select p from Product p where p.description LIKE '%:query_string%' "
			+ " OR p.description LIKE '%:all_words%' "
			+ " OR p.name LIKE '%:query_string%' OR p.name LIKE '%:all_words%' "
			+ " AND  p.description = SUBSTRING(p.description, 1,:description_length) ")
	List<Product> productSearchAllWords(@Param("query_string") String query_string,
			@Param("all_words") String all_words, @Param("description_length") Integer description_length,
			Pageable pageable);

	@Query(value = "select p from Product p where p.name LIKE '%' || :query_string || '%' OR p.description LIKE '%' || :query_string || '%'  AND  p.description = SUBSTRING(p.description, 1,:description_length)")
	List<Product> productSearch(@Param("query_string") String query_string,
			@Param("description_length") Integer description_length, Pageable pageable);

	@Query(value = "select p from Product p where p.name LIKE '%' || :all_words|| '%' OR p.description LIKE '%' || :all_words || '%'  AND  p.description = SUBSTRING(p.description, 1,:description_length)")
	List<Product> productSearch2(@Param("all_words") String all_words,
			@Param("description_length") Integer description_length, Pageable pageable);

	@Query(value = "select new com.turing.ecommerce.DTO.ProductInDepartment(pp.name, pp.productId, pp.description, pp.price,"
			+ " pp.discountedPrice, pp.thumbnail) from Category c inner join ProductCategory p on c.categoryId = p.id.categoryId inner join Product pp "
			+ " on p.id.productId = pp.productId where c.categoryId = :categoryId AND pp.description = SUBSTRING(pp.description, 1,:description_length)")
	List<ProductInDepartment> productSearchCategory(@Param("categoryId") Integer categoryId,
			@Param("description_length") Integer description_length, Pageable pageable);

	

	@Query(value = "select new com.turing.ecommerce.DTO.ProductInDepartment(f.name, f.productId, f.description, f.price, "
			+ " f.discountedPrice, f.thumbnail) from Category c inner join ProductCategory e on "
			+ "c.categoryId = e.id.categoryId inner join Product f on e.id.productId = f.productId "
			+ "where c.departmentId = :departmentId and f.description = SUBSTRING(f.description, 1,:description_length) ")

	List<ProductInDepartment> productSearchDepartment(@Param("departmentId") Integer departmentId,
			@Param("description_length") Integer description_length, Pageable pageable);

	
	@Query(value ="SELECT new com.turing.ecommerce.DTO.ProductDetail(p.productId, p.name, p.description, p.price, p.discountedPrice,"
			+ " p.image, p.image2) FROM Product p WHERE  p.productId = :productId")
	Optional<ProductDetail> getProductDetails(@Param("productId") Integer productId);
	
	 @Query(value ="Select new com.turing.ecommerce.DTO.ProductLocations(b.categoryId, b.name, a.departmentId, a.name) "
	 		+ " from Department a inner join Category b on a.departmentId = b.departmentId inner join"
	 		+ "  ProductCategory c on b.categoryId = c.id.categoryId inner join Product p on c.id.productId = p.productId "
	 		+ "where p.productId = :productId")
	 Optional<ProductLocations> getProductLocations(@Param("productId") Integer productId);
	 
	 @Query(value="select new com.turing.ecommerce.DTO.ReviewDTO(c.name,b.review, b.rating, b.createdOn) "
	 		+ "from Review b inner join Customer c on b.customerId = c.customerId where b.productId = :productId ORDER BY b.createdOn ASC")
	 List<ReviewDTO> fetchProdReviews(@Param("productId")Integer productId);
	 
	 
	 
	 
}
