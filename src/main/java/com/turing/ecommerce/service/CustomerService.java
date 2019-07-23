/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.Optional;

import com.turing.ecommerce.model.Customer;

/**
 * @author frankukachukwu
 *
 */
public interface CustomerService {
	
    public Customer save(Customer customer);
   
    public Optional<Customer> findById(Integer custId);
    public Optional<Customer> findByEmail(String custname);

}
