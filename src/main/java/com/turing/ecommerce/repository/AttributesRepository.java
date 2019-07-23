/**
 * 
 */
package com.turing.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.turing.ecommerce.DTO.AttributeDTO;
import com.turing.ecommerce.model.Attribute;


/**
 * 
 * Categories Repository is for all data access operations for Categories.
 * 
 * @author thankgodukachukwu
 *
 */
@RestResource(exported = false)
public interface AttributesRepository extends JpaRepository<Attribute, Integer> {

	/**
	 * Retrieves all attributes.
	 *
	 * 
	 * @return the attribute objects
	 */
	@Query("select new com.turing.ecommerce.DTO.AttributeDTO(a.attributeId, a.name)"
			+ " from Attribute a ")
	List<AttributeDTO> findAllAttributes();
	
	/**
	 * 
	 *
	 * @param attributeid
	 * @return the attribute object
	 */
	@Query("select new com.turing.ecommerce.DTO.AttributeDTO(a.attributeId, a.name)"
			+ " from Attribute a where a.attributeId = :attributeId")
	 Optional<AttributeDTO> findAttribute(@Param("attributeId") Integer attributeId);

	/**
	 * Retrieves a category given a department id.
	 * 
	 * SELECT b.attribute_value_id, b.value FROM attribute a inner join
	 * attribute_value b on a.attribute_id = b.attribute_id where a.attribute_id = 2
	 *
	 * @param attribute id to retrieve the details
	 * @return the values 
	 */
	@Query("SELECT  new com.turing.ecommerce.DTO.AttributeDTO(b.attributeValueId, b.value) "
			+ "FROM Attribute a inner join a.values b on a.attributeId = b.attributeId "
			+ " where a.attributeId = :attributeId")
	public List<AttributeDTO> getValuesByAttributeId(@Param("attributeId") Integer attributeId);
	
	
	

}
