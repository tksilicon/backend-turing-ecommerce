package com.turing.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.model.Review;
import com.turing.ecommerce.repository.ReviewRepository;

/**
 * 
 * @author frankukachukwu
 *
 */


@Service("reviewImplService")
public class ReviewServiceImpl implements ReviewService {

	
	
	@Autowired 
	private ReviewRepository reviewRepository;
	
	/**
	 * 
	 */
	
	@Override
	public Review save(Review review) {
		
		return reviewRepository.save(review);
	}
	

}
