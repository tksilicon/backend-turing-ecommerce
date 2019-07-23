/**
 * 
 */
package com.turing.ecommerce.DTO;



import org.hibernate.validator.constraints.CreditCardNumber;

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
public class CustomerCreditCardForm {
	
	@CreditCardNumber
	private String creditCard;

}
