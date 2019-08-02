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
public class StripePayObject {
	
	
	private String status;
	private Integer amount;
	private String description;
	private Integer orderId;

}
