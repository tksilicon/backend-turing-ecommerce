/**
 * 
 */
package com.turing.ecommerce.DTO;



import lombok.Data;

/**
 * @author frankukachukwu
 *
 */
@Data
public class ProductReviewDTO {
	
	
	private String review;
	private short rating;
	
	public ProductReviewDTO() {
		super();
	}
	
	

}
