package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
	
	private Integer productId;
	private String name;
	private String description;
	
	private BigDecimal price;
	private BigDecimal discountedPrice;
	private String image;
	private String image2;
	
	
	
	
	
	
	

}
