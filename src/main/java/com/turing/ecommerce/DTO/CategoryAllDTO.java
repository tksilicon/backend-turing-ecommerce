/**
 * 
 */
package com.turing.ecommerce.DTO;

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
public class CategoryAllDTO {
	
	private int categoryId;

	private int departmentId;

	private String description;

	private String name;
	
	


}
