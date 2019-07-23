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
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3395261898617767047L;
	
	

	public CustomerNotFoundException() {
		super();
		
	}



	public  CustomerNotFoundException(String message) {
		super(message);

	}

}
