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
public class ProductLocations {
	
	@ApiModelProperty(example="1", position=1)
	private Integer categoryId;
	@ApiModelProperty(example="French", position=2)
	private String categoryName;
	@ApiModelProperty(example="1", position=3)
	private Integer departmentId;
	@ApiModelProperty(example="Regional", position=4)
	private String departmentName;
	
	
	

}
