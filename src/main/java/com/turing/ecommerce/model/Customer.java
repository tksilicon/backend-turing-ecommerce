package com.turing.ecommerce.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModelProperty;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "TABLE_GEN", table = "T_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "CUST_ID", initialValue = 1, allocationSize = 1)

	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "address_1")
	private String address1;

	@Column(name = "address_2")
	private String address2;

	private String city;

	private String country;

	@Lob
	@Column(name = "credit_card")
	@CreditCardNumber
	private String creditCard;

	@Column(name = "day_phone")
	private String dayPhone;

	@Email
	private String email;

	@Column(name = "eve_phone")
	private String evePhone;

	@Column(name = "mob_phone")
	private String mobPhone;

	@NotEmpty(message = "Review cannot be emply")
	@NotBlank(message = "Review cannot be emply")
	private String name;

	private String password;

	@Column(name = "postal_code")
	private String postalCode;

	private String region;

	@Column(name = "shipping_region_id")
	private int shippingRegionId;

	@ApiModelProperty(required = false, hidden = true)
	@Transient
	private List<String> roles = Arrays.asList("USER");

	

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getDayPhone() {
		return this.dayPhone;
	}

	public void setDayPhone(String dayPhone) {
		this.dayPhone = dayPhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEvePhone() {
		return this.evePhone;
	}

	public void setEvePhone(String evePhone) {
		this.evePhone = evePhone;
	}

	public String getMobPhone() {
		return this.mobPhone;
	}

	public void setMobPhone(String mobPhone) {
		this.mobPhone = mobPhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getShippingRegionId() {
		return this.shippingRegionId;
	}

	public void setShippingRegionId(int shippingRegionId) {
		this.shippingRegionId = shippingRegionId;
	}

	// userdetails methods

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public String getUsername() {

		return this.getEmail();
	}

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@ApiModelProperty(required = false, hidden = true)
	@Override
	public boolean isEnabled() {
		return true;
	}

	@ApiModelProperty(required = false, hidden = true)
	public List<String> getRoles() {
		return roles;
	}

	
	

}