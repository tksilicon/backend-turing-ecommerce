package com.turing.ecommerce.DTO;



public class AttributesProductDTO {
	

	private String name;
	
	private int attributeValueId;

	private String value;
	
	

	public AttributesProductDTO() {
		
	}
	
	

	public AttributesProductDTO(String name, int attributeValueId, String value) {
		super();
		this.name = name;
		this.attributeValueId = attributeValueId;
		this.value = value;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttributeValueId() {
		return attributeValueId;
	}

	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
