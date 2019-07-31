/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;

import com.turing.ecommerce.model.Product;

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
public class ProductAll {

	@ApiModelProperty(example="1")
	private int productId;
	
	@ApiModelProperty(example="Chartres Cathedral")
	private String name;

	@ApiModelProperty(example="\"The Fur Merchants\". Not all the beautiful"
			+ " stained glass in the great cathedrals depicts "
			+ "saints and angels! Lay aside your furs for the summer and wear this beautiful T-shirt!\"")
	private String description;
	
	@ApiModelProperty(example="16.95")
	private BigDecimal price;

	@ApiModelProperty(example="15.95")
	@Column(name = "discounted_price")
	private BigDecimal discountedPrice;
	
	@ApiModelProperty(example="chartres-cathedral-thumbnail.gif")
	private String thumbnail;
}
