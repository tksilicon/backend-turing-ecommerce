package com.turing.ecommerce.DTO;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;


public class CategoryDTO {

	private int categoryId;

	private int departmentId;

	private String description;

	private String name;
	
	private int productId;

	public CategoryDTO(int categoryId, int departmentId,  String name, String description) {
		super();
		this.categoryId = categoryId;
		this.departmentId = departmentId;
		this.description = description;
		this.name = name;
	}

	public CategoryDTO(int categoryId, int departmentId, String name) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
