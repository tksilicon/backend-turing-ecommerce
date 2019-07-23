/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.ecommerce.DTO.ShippingRegionDTO;
import com.turing.ecommerce.model.Shipping;
import com.turing.ecommerce.model.ShippingRegion;
import com.turing.ecommerce.repository.ReviewRepository;
import com.turing.ecommerce.repository.ShippingRepository;

/**
 * @author thankgodukachukwu
 *
 */

@Service("shippingServiceImpl")
public class ShippingServiceImpl implements ShippingService{
	
	@Autowired 
	private ShippingRepository shippingRepository;

	

	/* (non-Javadoc)
	 * @see com.turing.ecommerce.service.ShippingService#getShippingRegion(java.lang.String)
	 */
	@Override
	public List<Shipping> getShippingRegion(Integer shippingRegionId) {
		
		return shippingRepository.findByShippingRegionId(shippingRegionId);
	}

}
