package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attribute_value database table.
 * 
 */
@Entity
@Table(name="attribute_value")
@NamedQuery(name="AttributeValue.findAll", query="SELECT a FROM AttributeValue a")
public class AttributeValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_value_id")
	private int attributeValueId;

	@Column(name="attribute_id")
	private int attributeId;

	private String value;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", insertable = false, updatable = false, nullable = false)
	private Attribute attribute;

	public AttributeValue() {
	}

	public int getAttributeValueId() {
		return this.attributeValueId;
	}

	public void setAttributeValueId(int attributeValueId) {
		this.attributeValueId = attributeValueId;
	}

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	

}