package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_category database table.
 * 
 */
@Entity
@Table(name="product_category")
@NamedQuery(name="ProductCategory.findAll", query="SELECT p FROM ProductCategory p")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductCategoryPK id;
	

	//@OneToOne(targetEntity=Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productCategory")
	//private Product product;

	
	
	
	public ProductCategory() {
	}
	
	
	public ProductCategoryPK getId() {
		return this.id;
	}
	

	public void setId(ProductCategoryPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id.getProductId() != (other.id.getProductId()))
			return false;
		return true;
	}

	


	
	


	
	

	
	
	
	

}