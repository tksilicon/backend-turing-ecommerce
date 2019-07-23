/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.model.Tax;
import com.turing.ecommerce.repository.TaxRepository;

/**
 * @author frankukachukwu
 *
 */
@Service("taxServiceImpl")
public class TaxServiceImpl implements TaxService {
	
	@Autowired
	TaxRepository taxRepository;

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.TaxService#getAllTaxes()
	 */
	@Override
	public List<Tax> getAllTaxes() {
		
		return taxRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.TaxService#getTax(java.lang.Integer)
	 */
	@Override
	public Optional<Tax> getTax(Integer taxId) {
		
		return  taxRepository.findById(taxId);
	}

}
