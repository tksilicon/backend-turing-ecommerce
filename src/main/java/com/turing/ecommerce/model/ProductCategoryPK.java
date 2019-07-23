package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the product_category database table.
 * 
 */
@Embeddable
public class ProductCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_id")
	private int productId;

	@Column(name="category_id")
	
	private int categoryId;
	
	

	public ProductCategoryPK() {
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductCategoryPK)) {
			return false;
		}
		ProductCategoryPK castOther = (ProductCategoryPK)other;
		return 
			(this.productId == castOther.productId)
			&& (this.categoryId == castOther.categoryId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productId;
		hash = hash * prime + this.categoryId;
		
		return hash;
	}
	
	
}