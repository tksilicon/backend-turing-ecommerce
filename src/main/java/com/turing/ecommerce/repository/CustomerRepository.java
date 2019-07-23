/**
 * 
 */
package com.turing.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.turing.ecommerce.model.Customer;


/**
 * @author thankgodukachukwu
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	 Optional<Customer> findByEmail(String email);

}
