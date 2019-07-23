package com.turing.ecommerce.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductReviewsException extends RuntimeException {
	
	private static final long serialVersionUID = -7870205355373585854L;

	public ProductReviewsException(String message) {
		super(message);
		
	}

}
