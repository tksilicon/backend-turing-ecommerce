package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the shopping_cart database table.
 * 
 */
@Entity
@Table(name="shopping_cart")
@NamedQuery(name="ShoppingCart.findAll", query="SELECT s FROM ShoppingCart s")
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private int itemId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="added_on")
	private Date addedOn;

	private String attributes;

	@Column(name="buy_now")
	private byte buyNow;

	@Column(name="cart_id")
	private String cartId;

	@Column(name="product_id")
	private int productId;

	private int quantity;

	public ShoppingCart() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Date getAddedOn() {
		return this.addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getAttributes() {
		return this.attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public byte getBuyNow() {
		return this.buyNow;
	}

	public void setBuyNow(byte buyNow) {
		this.buyNow = buyNow;
	}

	public String getCartId() {
		return this.cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}