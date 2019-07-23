package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_attribute database table.
 * 
 */
@Entity
@Table(name="product_attribute")
@NamedQuery(name="ProductAttribute.findAll", query="SELECT p FROM ProductAttribute p")
public class ProductAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductAttributePK id;

	public ProductAttribute() {
	}

	public ProductAttributePK getId() {
		return this.id;
	}

	public void setId(ProductAttributePK id) {
		this.id = id;
	}

}