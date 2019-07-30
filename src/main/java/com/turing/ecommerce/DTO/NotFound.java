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
 * @author frankukachukwu
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class NotFound {
	
	@ApiModelProperty
	private String message;

}
