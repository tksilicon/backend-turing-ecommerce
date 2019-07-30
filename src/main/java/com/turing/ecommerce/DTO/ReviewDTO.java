/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author thankgodukachukwu
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	private String name;
	private String review;
	private short rating;
	private Date createdOn;
	
	
	
	
	
}



