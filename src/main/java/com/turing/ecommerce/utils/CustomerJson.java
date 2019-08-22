/**
 * 
 */
package com.turing.ecommerce.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author thankgodukachukwu
 *
 */
@Data
public class CustomerJson {
	
	@JsonProperty("access_token")
	public String accessToken;

}
