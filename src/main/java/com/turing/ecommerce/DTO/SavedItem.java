/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

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
public class SavedItem {
	
	private Integer itemId;
	private String name;
	private String attributes;
	private BigDecimal price;

}
