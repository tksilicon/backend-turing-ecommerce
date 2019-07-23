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

public class CustomerOrderDTO {

	private int orderId;

	private BigDecimal totalAmount;

	private Date createdOn;

	private Date shippedOn;

	private int status;

	private String name;

}
