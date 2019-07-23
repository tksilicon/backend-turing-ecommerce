/**
 * 
 */
package com.turing.ecommerce.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.turing.ecommerce.validator.PhoneNumberCheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author frankukachukwu
 *
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateForm {

	@NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
	private String name;

	@Email
	private String email;

	@NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
	private String password;

	@NotBlank
	@PhoneNumberCheck
	private String dayPhone;

	private String evePhone;

	private String mobPhone;

}
