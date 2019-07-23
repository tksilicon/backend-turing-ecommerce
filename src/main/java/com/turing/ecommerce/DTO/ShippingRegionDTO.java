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
public class ShippingRegionDTO {

	private int shippingId;
	private String shippingType;
	private BigDecimal shippingCost;
	private int shippingRegionId;

}
