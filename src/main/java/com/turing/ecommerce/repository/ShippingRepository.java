/**
 * 
 */
package com.turing.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turing.ecommerce.model.Shipping;

/**
 * @author frankukachukwu
 *
 */
public interface ShippingRepository extends  JpaRepository<Shipping, Integer>{
	
	
	
	public List<Shipping> findByShippingRegionId(int shippingRegionId);
	

}
