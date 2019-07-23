package com.turing.ecommerce.DTO;

import java.math.BigDecimal;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;


public class CategoryProductDTO {

	private int categoryId;

	private int departmentId;

	private String name;
	
	

	

	public CategoryProductDTO() {
		
	}

	public CategoryProductDTO(int categoryId, int departmentId, String name) {
		super();
		this.categoryId = categoryId;
		this.departmentId = departmentId;
		this.name = name;
	}

	

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

	

}

