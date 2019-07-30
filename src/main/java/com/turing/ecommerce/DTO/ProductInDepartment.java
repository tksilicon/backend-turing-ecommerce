package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInDepartment {

	private String name;
	private Integer productId;
	private String description;
	private BigDecimal price;
	private BigDecimal discountedPrice;
	private String thumbnail;
	
	
	
	
	

}
