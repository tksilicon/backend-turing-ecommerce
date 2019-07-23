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
public class CustomerExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6216105494039783312L;

	public CustomerExistException() {
		super();
		
	}

	public CustomerExistException(String message) {
		super(message);
		
	}
	
	

}
