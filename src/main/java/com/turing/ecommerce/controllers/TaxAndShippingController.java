/**
 * 
 */
package com.turing.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.model.Shipping;
import com.turing.ecommerce.model.ShippingRegion;
import com.turing.ecommerce.model.Tax;
import com.turing.ecommerce.service.ShippingRegionService;
import com.turing.ecommerce.service.ShippingService;
import com.turing.ecommerce.service.TaxService;

import io.swagger.annotations.Api;

/**
 * @author thankgodukachukwu
 *
 */
@Api(value = "Everything about Taxes and Shipping")
@RestController
public class TaxAndShippingController {
	
	
	@Resource(name = "shippingRegionServiceImpl")
	private ShippingRegionService shippingRegionService;
	
	@Resource(name = "shippingServiceImpl")
	private ShippingService shippingService;
	
	@Resource(name = "taxServiceImpl")
	TaxService taxService; 
	
	
	/*
	 * API endpoint to get taxes
	 */
	@GetMapping(path = "/api/tax")
	public ResponseEntity<List<Tax>> getAllTaxes() {

	
		return ResponseEntity.ok(taxService.getAllTaxes());
	}

	
	/*
	 * API endpoint to get SavedItems
	 */
	@GetMapping(path = "/api/tax/{tax_id}")
	public ResponseEntity<Optional<Tax>> getSaved(
			@PathVariable(name = "tax_id", required = true) Integer taxId) {

		
		
		return ResponseEntity.ok(taxService.getTax(taxId));
	}
	
	/*
	 * API endpoint to get Shipping Regions
	 */
	@GetMapping(path = "/api/shipping/regions")
	public ResponseEntity<List<ShippingRegion>> getShippingRegions() {

		
		
		return ResponseEntity.ok(shippingRegionService.getAllRegions());
	}
	
	
	/*
	 * API endpoint to get Shipping Regions
	 */
	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/shipping/regions/{shipping_region_id}")
	public ResponseEntity<List<Shipping>> getShippingRegionsById(
			@PathVariable(name = "shipping_region_id", required = true) Integer shippingRegionId) {

		
		
		return ResponseEntity.ok(shippingService.getShippingRegion(shippingRegionId));
	}
	
	
	
}
