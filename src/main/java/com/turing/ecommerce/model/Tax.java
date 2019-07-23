package com.turing.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tax database table.
 * 
 */
@Entity
@NamedQuery(name="Tax.findAll", query="SELECT t FROM Tax t")
public class Tax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tax_id")
	private int taxId;

	@Column(name="tax_percentage")
	private BigDecimal taxPercentage;

	@Column(name="tax_type")
	private String taxType;

	public Tax() {
	}

	public int getTaxId() {
		return this.taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public BigDecimal getTaxPercentage() {
		return this.taxPercentage;
	}

	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getTaxType() {
		return this.taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

}