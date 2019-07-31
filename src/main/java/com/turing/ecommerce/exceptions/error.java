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

	@ApiModelProperty(example="USR_02,", position=1)
	private String code;

	@ApiModelProperty(example="The field example is empty.", position=2)
	private String message;

	@ApiModelProperty(example="example", position=3)
	private String field;
	
	@ApiModelProperty(example="500", position=4)
	private String status;
	
	
    
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



