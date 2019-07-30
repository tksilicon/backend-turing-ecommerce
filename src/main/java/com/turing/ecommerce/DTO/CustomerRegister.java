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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class CustomerRegister {
	@ApiModelProperty
	private Schema customer;
	@ApiModelProperty
	private String accesstoken;
	@ApiModelProperty
	private String expires_in;

}
