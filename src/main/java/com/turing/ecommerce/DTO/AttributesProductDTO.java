package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttributesProductDTO {
	
	@ApiModelProperty( example="Color")
	private String name;
	@ApiModelProperty( example="6")
	private int attributeValueId;
	@ApiModelProperty(example="White")
	private String value;
	
	

	

}
