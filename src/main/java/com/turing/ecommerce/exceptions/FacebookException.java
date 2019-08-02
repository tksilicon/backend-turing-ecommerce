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

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FacebookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7134072463298887020L;

	public FacebookException() {
		super();
		
	}

	public FacebookException(String message) {
		super(message);
		
	}
	
	

}
