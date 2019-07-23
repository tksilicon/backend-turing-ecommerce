package com.turing.ecommerce.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.turing.ecommerce.model.Review;

/**
 * 
 * @author frankukachukwu
 *
 */

public interface ReviewService {
	
	/**
	 * 
	 * @param review
	 * @return
	 */
   @PreAuthorize("hasRole('ROLE_USER')")
   public Review save(Review review);
   

}
