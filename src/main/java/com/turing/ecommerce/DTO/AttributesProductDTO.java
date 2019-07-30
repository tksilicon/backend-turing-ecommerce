package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttributesProductDTO {
	

	private String name;
	
	private int attributeValueId;

	private String value;
	
	

	

}
