package com.turing.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.turing.ecommerce.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

}
