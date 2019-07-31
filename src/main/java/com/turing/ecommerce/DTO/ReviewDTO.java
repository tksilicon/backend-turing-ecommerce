/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thankgodukachukwu
 *
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	@ApiModelProperty(example = "Eder Taveira", position=1)
	private String name;
	@ApiModelProperty(example = "That's a good product. The best for me.", position=2)
	private String review;
	@ApiModelProperty(example = "4", position=1)
	private short rating;
	@ApiModelProperty(example = "2019-02-17 13:57:29", position=1)
	private Date createdOn;
	
	
	
	
	
}



