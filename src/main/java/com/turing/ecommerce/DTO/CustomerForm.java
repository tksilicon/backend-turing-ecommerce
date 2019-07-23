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
public class CustomerForm {
	
	private String name;
	private String email;
	private String password;
	
	

}
