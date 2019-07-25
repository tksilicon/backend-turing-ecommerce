/**
 * 
 */
package com.turing.ecommerce.exceptions;



import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * @author thankgodukachukwu
 *
 */
@Api
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class error  {

	
	private String status;
	
	private String code;

	private String message;

	private String field;
	
	
    
	public error(String status, String code, String message, String field) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.field = field;
	}



	public error(String code, String message, String field) {
		super();
		this.code = code;
		this.message = message;
		this.field = field;
	}
	

	
}



