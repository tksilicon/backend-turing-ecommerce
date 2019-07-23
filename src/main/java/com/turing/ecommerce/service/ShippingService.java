/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;

import com.turing.ecommerce.model.Shipping;
import com.turing.ecommerce.model.ShippingRegion;

/**
 * @author thankgodukachukwu
 *
 */
public interface ShippingService {
	
	
	public List<Shipping> getShippingRegion(Integer shippingRegionId);

}
