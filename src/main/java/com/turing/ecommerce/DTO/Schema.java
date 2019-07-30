/**
 * 
 */
package com.turing.ecommerce.DTO;

import com.turing.ecommerce.model.Customer;

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
public class Schema {
	@ApiModelProperty
	private Customer customer;

}
