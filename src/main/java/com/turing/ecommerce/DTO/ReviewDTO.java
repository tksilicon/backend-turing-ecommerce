/**
 * 
 */
package com.turing.ecommerce.DTO;

import java.util.Date;

/**
 * @author thankgodukachukwu
 *
 */
public class ReviewDTO {
	
	private String name;
	private String review;
	private short rating;
	private Date createdOn;
	
	
	public ReviewDTO() {
		super();
	}
	public ReviewDTO(String name, String review, short rating, Date createdOn) {
		super();
		this.name = name;
		this.review = review;
		this.rating = rating;
		this.createdOn = createdOn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public short getRating() {
		return rating;
	}
	public void setRating(short rating) {
		this.rating = rating;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	
}



