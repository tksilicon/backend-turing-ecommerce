package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the shipping database table.
 * 
 */
@Entity
@NamedQuery(name="Shipping.findAll", query="SELECT s FROM Shipping s")
public class Shipping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shipping_id")
	private int shippingId;

	@Column(name="shipping_cost")
	private BigDecimal shippingCost;

	@Column(name="shipping_region_id")
	private int shippingRegionId;

	@Column(name="shipping_type")
	private String shippingType;

	public Shipping() {
	}

	public int getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(int shippingId) {
		this.shippingId = shippingId;
	}

	public BigDecimal getShippingCost() {
		return this.shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public int getShippingRegionId() {
		return this.shippingRegionId;
	}

	public void setShippingRegionId(int shippingRegionId) {
		this.shippingRegionId = shippingRegionId;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

}