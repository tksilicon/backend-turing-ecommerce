/**
 * 
 */
package com.turing.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author frankukachukwu
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7586739662214301096L;

	public ItemNotFoundException() {
		super();
		
	}

	public ItemNotFoundException(String message) {
		super(message);
		
	}

}
