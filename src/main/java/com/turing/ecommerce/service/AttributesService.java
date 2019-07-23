/**
 * 
 */
package com.turing.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.DTO.CategoryDTO;
import com.turing.ecommerce.model.Attribute;

/**
 * @author thankgodukachukwu
 *
 */
public interface AttributesService {
	
	
	
	Optional<AttributeDTO> findById(Integer attributeId);

	public List<AttributeDTO> getAll();
	
	public List<AttributeDTO> getValuesByAttributeId(Integer attributeId);

}
