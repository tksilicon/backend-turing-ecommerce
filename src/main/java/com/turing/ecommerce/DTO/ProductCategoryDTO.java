package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

public class ProductCategoryDTO {

	private String name;
	private Integer productId;
	private String description;
	private BigDecimal price;
	private BigDecimal discountedPrice;
	private String thumbnail;
	
	
	public ProductCategoryDTO() {
		
	}


	public ProductCategoryDTO(String name, Integer productId, String description, BigDecimal price,
			BigDecimal discountedPrice, String thumbnail) {
		super();
		this.name = name;
		this.productId = productId;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.thumbnail = thumbnail;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
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


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	

}
