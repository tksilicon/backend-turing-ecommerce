package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * The persistent class for the product database table.
 * 
 */
@ApiModel
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
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
	
	

	

	@ApiModelProperty(example="chartres-cathedral.gif")
	private String image;

	@ApiModelProperty(example="chartres-cathedral2.gif")
	@Column(name = "image_2")
	private String image2;
	
	@ApiModelProperty(example="0")
	private short display;
	
	@ApiModelProperty(example="chartres-cathedral-thumbnail.gif")
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