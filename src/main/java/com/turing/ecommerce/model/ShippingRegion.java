package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shipping_region database table.
 * 
 */
@Entity
@Table(name="shipping_region")
@NamedQuery(name="ShippingRegion.findAll", query="SELECT s FROM ShippingRegion s")
public class ShippingRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shipping_region_id")
	private int shippingRegionId;

	@Column(name="shipping_region")
	private String shippingRegion;

	public ShippingRegion() {
	}

	public int getShippingRegionId() {
		return this.shippingRegionId;
	}

	public void setShippingRegionId(int shippingRegionId) {
		this.shippingRegionId = shippingRegionId;
	}

	public String getShippingRegion() {
		return this.shippingRegion;
	}

	public void setShippingRegion(String shippingRegion) {
		this.shippingRegion = shippingRegion;
	}

}