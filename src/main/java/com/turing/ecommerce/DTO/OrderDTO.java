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
public class OrderDTO {

	private int orderId;

	private String authCode;

	private String comments;

	private Date createdOn;

	private int customerId;

	private String reference;

	private Date shippedOn;

	private int shippingId;

	private int status;

	private int taxId;

	private BigDecimal totalAmount;

}
