/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

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
public class ShoppingCartForm {
	
	private String cartId;
	private Integer productId;
	private String attributes;
	

}
