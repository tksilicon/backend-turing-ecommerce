/**
 * 
 */
package com.turing.ecommerce.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thankgodukachukwu
 *
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValueDTO {

	@ApiModelProperty(example="1")
	private Integer attributeValueId;
	@ApiModelProperty(example="Size")
	private String value;
}
