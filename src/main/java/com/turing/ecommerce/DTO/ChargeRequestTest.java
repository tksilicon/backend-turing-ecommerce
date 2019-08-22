/**
 * 
 */
package com.turing.ecommerce.DTO;



import lombok.Data;

/**
 * @author thankgodukachukwu
 *
 */
@Data
public class ChargeRequestTest {

	private String aStripeToken;
	private Integer order_id;
	private String description;

	private int amount;

	private String currency;

}
