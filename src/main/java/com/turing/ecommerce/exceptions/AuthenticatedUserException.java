/**
 * 
 */
package com.turing.ecommerce.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author frankukachukwu
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticatedUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3404305349522270105L;
	
	
	public AuthenticatedUserException(String message) {
		super(message);


	}


}
