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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderDetailNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085639311710563304L;

	public OrderDetailNotFoundException() {
		super();
		
	}

	public OrderDetailNotFoundException(String message) {
		super(message);
		
	}
	

}
