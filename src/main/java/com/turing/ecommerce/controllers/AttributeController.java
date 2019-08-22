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
import com.turing.ecommerce.exceptions.error;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Attributes Controller for all Rest APIs endpoints related to attributes.
 * 
 * @author thankGodukachukwu
 * 
 */

@Api(value = "Everything about Attributes")
@RestController
public class AttributeController {

	@Resource(name = "attributeImplService")
	private AttributesService attributesService;

	@Resource(name = "attributeDAOServiceImpl")
	private AttributeDAOServiceImpl attributesService2;

	@Resource(name = "attributeDAOServiceImpl")
	private AttributeDAOServiceImpl attributesService3;

	/*
	 * API to return all attributes
	 */
	@ApiOperation(value = "Get attributes list", response = Attribute.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of Attribute Objects", response = AttributeDTO.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	@GetMapping(path = "/api/attributes")
	public ResponseEntity<List<AttributeDTO>> getAll() {
		return ResponseEntity.ok(attributesService.getAll());
	}

	/*
	 * API to return an attribute by id
	 */
	@ApiOperation(value = "Get Attribute by id", response = Attribute.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return Attribute Object", response = AttributeDTO.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	@GetMapping(path = "/api/attributes/{attribute_id}")
	public ResponseEntity<Optional<AttributeDTO>> getById(
			@PathVariable(name = "attribute_id", required = true) Integer attributeId) {

		Optional<AttributeDTO> attribute = attributesService.findById(attributeId);

		if (!attribute.isPresent()) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(attribute);
	}

	/*
	 * API to return values of attribute_id
	 */

	@ApiOperation(value = "Get Values Attribute from Atribute", response = Attribute.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a list of Attribute Values", response = AttributeValueDTO.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	@GetMapping(path = "/api/attributes/values/{attribute_id}")
	public ResponseEntity<List<AttributeDTO>> getValuesByAttributeId(
			@PathVariable(name = "attribute_id", required = true) Integer attributeId) {

		return ResponseEntity.ok(attributesService.getValuesByAttributeId(attributeId));

	}

	/*
	 * API to return attributes value array of product Id
	 */
	@ApiOperation(value = "Get all Attributes with Product ID", response = Attribute.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a array of Values of Attribute Objects", response = AttributesProductDTO.class),
			@ApiResponse(code = 400, message = "Return a error object", response = error.class) })
	@GetMapping(path = "/api/attributes/inProduct/{product_id}")
	public ResponseEntity<List<AttributesProductDTO>> getAttributeValuesByProductId(
			@PathVariable(name = "product_id", required = true) Integer productId) {

		return ResponseEntity.ok(attributesService2.findAttributesByProductId(productId));

	}

}
