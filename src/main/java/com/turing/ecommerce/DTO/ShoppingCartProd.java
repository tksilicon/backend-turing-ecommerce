/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;

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
public class ShoppingCartProd {
	
	private Integer itemId;
	private String cartdId;
	private Integer productId;
	private String attributes;
	private Date addedOn;
	private byte buyNow;
	
	

}
