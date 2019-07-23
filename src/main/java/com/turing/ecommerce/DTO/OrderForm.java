/**
 * 
 */
package com.turing.ecommerce.DTO;

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
public class OrderForm {

	
	private String cartId;
	private Integer shipppingId;
	private Integer taxId;
	
	
}

