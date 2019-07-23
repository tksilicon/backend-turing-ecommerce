package com.turing.ecommerce.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductsGetProductsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018647985642888160L;

	private final List<Object> params;

	public ProductsGetProductsException(String message, List<Object> params) {
		super(message);

		this.params = params;

	}

	public List<Object> getParams() {
		return params;
	}
}
