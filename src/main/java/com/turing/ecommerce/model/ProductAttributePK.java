package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the product_attribute database table.
 * 
 */
@Embeddable
public class ProductAttributePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_id")
	private int productId;

	@Column(name="attribute_value_id")
	private int attributeValueId;

	public ProductAttributePK() {
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAttributeValueId() {
		return this.attributeValueId;
	}
	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}
    @Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductAttributePK)) {
			return false;
		}
		ProductAttributePK castOther = (ProductAttributePK)other;
		return 
			(this.productId == castOther.productId)
			&& (this.attributeValueId == castOther.attributeValueId);
	}
    @Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productId;
		hash = hash * prime + this.attributeValueId;
		
		return hash;
	}
}