/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
	private String name;
	
	@Email
	private String email;

	@NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
	private String password;
	

}
