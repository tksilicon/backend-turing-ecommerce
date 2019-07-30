/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import javax.persistence.Column;

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
public class CartWithProduct {
	
	//shoppingcart
	private Integer itemId;
	
	//order detail
	private String name;
	
	//shoppingcart
	private String attributes; 
	private int productId;
	
	//order detail
	private BigDecimal unitCost;

	//Shoppingcart
	private int quantity;
	
	//product 
	private String image;
	
	//calculated
	private BigDecimal subtotal;
	
	

	

}
