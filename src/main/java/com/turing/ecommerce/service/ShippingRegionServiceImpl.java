/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.model.ShippingRegion;
import com.turing.ecommerce.repository.ShippingRegionRepository;

/**
 * @author thankgodukachukwu
 *
 */
@Service("shippingRegionServiceImpl")
public class ShippingRegionServiceImpl implements ShippingRegionService {
	@Autowired 
	private ShippingRegionRepository shippingRepository;
	
	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.ShippingService#getAllRegions()
	 */
	@Override
	public List<ShippingRegion> getAllRegions() {
		
		return shippingRepository.findAll();
	}

}
