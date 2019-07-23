/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.repository.CustomerRepository;


/**
 * @Service("customerImplService")
 * @author frankukachukwu
 *
 */
@Service("customerImplService")
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired 
	private CustomerRepository customerRepository;
	
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	@Override
	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}


	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.CustomerService#findById(java.lang.Integer)
	 */
	@Override
	public Optional<Customer> findById(Integer custId) {
		
		return customerRepository.findById(custId);
	}


	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.CustomerService#findByName(java.lang.Integer)
	 */
	@Override
	public Optional<Customer> findByEmail(String email) {
		
		return customerRepository.findByEmail(email);
		
		
	}



}
