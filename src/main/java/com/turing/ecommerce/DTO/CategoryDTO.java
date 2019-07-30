package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

	private int categoryId;

	private int departmentId;

	private String name;
	private String description;

	
	
	

	

}
