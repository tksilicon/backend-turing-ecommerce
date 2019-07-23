package com.turing.ecommerce.DTO;

public class ProductLocationsDTO {
	
	private Integer categoryId;
	private String categoryName;
	private Integer departmentId;
	private String departmentName;
	public ProductLocationsDTO() {
		super();
	}
	public ProductLocationsDTO(Integer categoryId, String categoryName, Integer departmentId, String departmentName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	

}
