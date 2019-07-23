package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

public class ProductDetailsDTO {
	
	private Integer productId;
	private String name;
	private String description;
	
	private BigDecimal price;
	private BigDecimal discountedPrice;
	private String image;
	private String image2;
	
	
	
	public ProductDetailsDTO() {
		super();
	}


	public ProductDetailsDTO(Integer productId, String name, String description, BigDecimal price,
			BigDecimal discountedPrice, String image, String image2) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.image = image;
		this.image2 = image2;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}


	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}


	public String getImage1() {
		return image;
	}


	public void setImage1(String image) {
		this.image = image;
	}


	public String getImage2() {
		return image2;
	}


	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	
	
	

}
