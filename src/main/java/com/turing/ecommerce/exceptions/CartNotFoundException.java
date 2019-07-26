/**
 * 
 */
package com.turing.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author thankgodukachukwu
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4351548948366103996L;

	public CartNotFoundException() {
		super();
		
	}

	public CartNotFoundException(String message) {
		super(message);
		
	}

}
