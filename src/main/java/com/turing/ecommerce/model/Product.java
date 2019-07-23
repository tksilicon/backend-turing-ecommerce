package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
	private int productId;

	private String description;

	@Column(name = "discounted_price")
	private BigDecimal discountedPrice;

	private short display;

	private String image;

	@Column(name = "image_2")
	private String image2;

	private String name;

	private BigDecimal price;

	private String thumbnail;
	
	
	

	

	/**@OneToOne(fetch = FetchType.LAZY, optional = false)
	

	@JoinColumns({
	 @JoinColumn(name = "category_id", referencedColumnName="category_id", insertable=false, updatable=false, nullable = false),
	 @JoinColumn(name = "product_id", referencedColumnName="product_id", insertable=false, updatable=false, nullable = false)
	
	})


	private ProductCategory productCategory**/

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscountedPrice() {
		return this.discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public short getDisplay() {
		return this.display;
	}

	public void setDisplay(short display) {
		this.display = display;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	
	

}