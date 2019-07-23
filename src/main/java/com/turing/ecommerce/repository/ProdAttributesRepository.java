package com.turing.ecommerce.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.turing.ecommerce.DTO.AttributesProductDTO;


@RestResource(exported = false)
@Repository
public class ProdAttributesRepository implements ProdAttributeDAO {

	private final String FETCH_SQL_BY_ID = " select c.name , b.attribute_value_id, b.value "
			+ "from product_attribute a inner join attribute_value b on a.attribute_value_id = b.attribute_value_id "
			+ " inner join attribute c on b.attribute_id = c.attribute_id where a.product_id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<AttributesProductDTO> findByProductId(int id) {

	   List<AttributesProductDTO> attributes = new ArrayList<AttributesProductDTO>();

		List rows = jdbcTemplate.queryForList(FETCH_SQL_BY_ID, 1);
		for (Object row : rows) {
			Map m = (Map)row;
			AttributesProductDTO attribute = new AttributesProductDTO();
			attribute.setName((String) (m.get("name")));
			attribute.setAttributeValueId((Integer) m.get("attribute_value_id"));
			attribute.setValue((String) m.get("value"));
			attributes.add(attribute);
		}
	
		return attributes;

	}

}
