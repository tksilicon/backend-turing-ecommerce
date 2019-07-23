package com.turing.ecommerce.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.turing.ecommerce.DTO.CategoryProductDTO;

@RestResource(exported = false)
@Repository
public class ProdCategoryRepository implements ProdCatDAO{

	private final String FETCH_SQL_BY_ID = "select c.category_id, c.department_id , "
			+ "c.name from product_category p join category c on "
			+ "p.category_id = c.category_id where p.product_id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	

	public CategoryProductDTO findByProductId(int id) {
		return (CategoryProductDTO) jdbcTemplate.queryForObject(FETCH_SQL_BY_ID, new Object[] { id },
				new CategoryProductDTOMapper());
	}

}

class CategoryProductDTOMapper implements RowMapper {

	@Override
	public CategoryProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryProductDTO user = new CategoryProductDTO();
		user.setCategoryId(rs.getInt("category_id"));
		user.setName(rs.getString("name"));
		user.setDepartmentId(rs.getInt("department_id"));
		
		return user;
	}

}
