/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.turing.ecommerce.model.Tax;

/**
 * @author thankgodukachukwu
 *
 */
public interface TaxService {
	
	public List<Tax> getAllTaxes();
	public Optional<Tax> getTax(Integer taxId);
	

}
