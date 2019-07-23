package com.turing.ecommerce.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;


/**
 * The persistent class for the attribute database table.
 * 
 */
@Entity
@NamedQuery(name="Attribute.findAll", query="SELECT a FROM Attribute a")
public class Attribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="attribute_id")
	private int attributeId;

	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attribute")
	private Set<AttributeValue> values;

	public Attribute() {
	}

	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AttributeValue> getValues() {
		return values;
	}

	public void setValues(Set<AttributeValue> values) {
		this.values = values;
	}
	
	

}