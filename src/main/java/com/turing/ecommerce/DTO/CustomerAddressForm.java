/**
 * 
 */
package com.turing.ecommerce.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class CustomerAddressForm {
	
	

	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	@Column(name="address_1")
	private String address1;

	@Column(name="address_2")
	private String address2;

	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	private String city;
	
	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	private String region;

	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	private String country;

	@Column(name="postal_code")
	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	private String postalCode;

	@Column(name="shipping_region_id")
	@NotNull
	@Positive
	private int shippingRegionId;

}
