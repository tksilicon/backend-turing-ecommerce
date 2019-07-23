package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;




import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import java.util.Date;


/**
 * The persistent class for the review database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="review_id")
	
	private int reviewId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	@Column(name="customer_id")
	private int customerId;

	
	@Column(name="product_id")
	@NotNull(message="ProductId cannot be null")
	private int productId;
    
	@NotNull(message="rating cannot be empty or blank")
	@Digits(integer = 1, fraction = 0, message="rating should be between 1-5 and cannot be empty")
	@Min(1)
	@Max(5)
	private short rating;
    
	
	@Lob
	@NotEmpty (message="Review cannot be emply")
	@NotBlank(message="Review cannot be emply")
	private String review;

	public Review() {
	}

	public int getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public short getRating() {
		return this.rating;
	}

	public void setRating(short rating) {
		this.rating = rating;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}