package com.turing.ecommerce.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {
	@ApiModelProperty(example="1")
	private int attributeId;

	@ApiModelProperty(example="Size")
	private String name;
	
	

		
	
	
	

}
