/**
 * 
 */
package com.turing.ecommerce.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author frankukachukwu
 *
 */

@Service("customCustomerDetailsService")
public class CustomCustomerDetailsService implements UserDetailsService  {
	
	private CustomerRepository customers;    
	
	
	public CustomCustomerDetailsService(CustomerRepository customers) {
        this.customers = customers;
    }    
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.customers.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + email + " not found"));
    }

}
