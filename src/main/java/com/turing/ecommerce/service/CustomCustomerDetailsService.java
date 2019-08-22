/**
 * 
 */
package com.turing.ecommerce.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.controllers.CustomerController;
import com.turing.ecommerce.model.Customer;
import com.turing.ecommerce.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author frankukachukwu
 *
 */
@Slf4j
@Service("customCustomerDetailsService")
public class CustomCustomerDetailsService implements UserDetailsService  {
	
	@Autowired
	private CustomerRepository customers;    
	
	
	
	
	@Override
    public UserDetails loadUserByUsername(String email)  {
	
      return this.customers.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + email + " not found"));
        		
        
       
              
    }

}
