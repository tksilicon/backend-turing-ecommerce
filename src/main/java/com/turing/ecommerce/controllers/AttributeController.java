package com.turing.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.turing.ecommerce.model.*;
import com.turing.ecommerce.service.*;
import com.turing.ecommerce.DTO.*;




/**
 * Attributes Controller for all Rest APIs endpoints related to attributes.
 * 
 * @author thankGodukachukwu
 * 
 */


@RestController
public class AttributeController {
	
	
	@Resource(name = "attributeImplService")
	private AttributesService attributesService;
	
	@Resource(name = "attributeDAOServiceImpl")
	private AttributeDAOServiceImpl attributesService2;
	
	
	/*
	 * API to return all attributes
	 */
	@GetMapping(path = "/api/attributes")
	public ResponseEntity<List<AttributeDTO>> getAll() {
		return ResponseEntity.ok(attributesService.getAll());
	}
	
	/*
	 * API to return an attribute by id
	 */
	@GetMapping(path = "/api/attributes/{attribute_id}")
	public ResponseEntity<Optional<AttributeDTO>> getById(
			@PathVariable(name = "attribute_id", required = true) Integer attributeId) {
		
	
		Optional<AttributeDTO> attribute = attributesService.findById(attributeId);

		if (!attribute.isPresent()) {
			//log.error("Attribute Id " + attributeId + " is not existing");
			//return ResponseEntity.notFound().build();
			
			//throw new DepartmentNotFoundException(departmentId);
		}

		return ResponseEntity.ok(attribute);
	}
	
	/*
	 * API to return values of attribute_id 
	 */
	@GetMapping(path = "/api/attributes/values/{attribute_id}")
	public ResponseEntity<List<AttributeDTO>> getValuesByAttributeId(
			@PathVariable(name = "attribute_id", required = true) Integer attributeId) {

		return ResponseEntity.ok(attributesService.getValuesByAttributeId(attributeId));

	}
	
	/*
	 * API to return attributes value array of product Id
	 */
	@GetMapping(path = "/api/attributes/inProduct/{product_id}")
	public ResponseEntity<List<AttributesProductDTO>> getAttributeValuesByProductId(
			@PathVariable(name = "product_id", required = true) Integer productId) {

		return ResponseEntity.ok(attributesService2.findAttributesByProductId(productId));

	}
	
	
	

	

}
