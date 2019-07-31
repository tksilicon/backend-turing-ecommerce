package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
	
	
	
	@ApiModelProperty(example="1", position =1)
	private int productId;
	
	@ApiModelProperty(example="Chartres Cathedral", position =2)
	private String name;

	@ApiModelProperty(position =3, example="\"The Fur Merchants\". Not all the beautiful"
			+ " stained glass in the great cathedrals depicts "
			+ "saints and angels! Lay aside your furs for the summer and wear this beautiful T-shirt!\"")
	private String description;
	
	@ApiModelProperty(example="16.95",position =4)
	private BigDecimal price;

	@ApiModelProperty(example="15.95", position =5)
	@Column(name = "discounted_price")
	private BigDecimal discountedPrice;
	
	@ApiModelProperty(example="chartres-cathedral.gif", position=6)
	private String image;

	@ApiModelProperty(example="chartres-cathedral2.gif", position=7)
	@Column(name = "image_2")
	private String image2;
	
	
	
	
	
	

}
