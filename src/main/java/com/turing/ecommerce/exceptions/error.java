/**
 * 
 */
package com.turing.ecommerce.exceptions;



import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author thankgodukachukwu
 *
 */
@ApiModel
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class error  {

	@ApiModelProperty(example="USR_02,")
	private String status;
	
	@ApiModelProperty(example="The field example is empty.,")
	private String code;

	@ApiModelProperty(example="example,")
	private String message;

	@ApiModelProperty(example="500")
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



