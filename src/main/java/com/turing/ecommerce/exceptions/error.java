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

@Data
@ApiModel
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class error  {

	@ApiModelProperty(value="USR_02,")
	private String status;
	
	@ApiModelProperty(value="The field example is empty.,")
	private String code;

	@ApiModelProperty(value="example,")
	private String message;

	@ApiModelProperty(value="500")
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



