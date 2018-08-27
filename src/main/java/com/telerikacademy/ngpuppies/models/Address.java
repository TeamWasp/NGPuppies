package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AddressID")
	private int addressId;
	
	@NotNull
	@Size(min = 5, max = 20)
	@Column(name = "Country")
	private String country;
	
	@NotNull
	@Size(min = 4, max = 15)
	@Column(name = "City")
	private String city;
	
	@NotNull
	@Size(min = 4, max = 4)
	@Column(name = "ZipCode")
	private String zipCode;
	
	@NotNull
	@Size(min = 10)
	@Column(name = "Street")
	private String street;
	
	public Address() {
	}
	
	public Address(String country, String city, String zipCode, String street) {
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
	}
	
	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Override
	public String toString() {
		return "Address{" +
				"addressId='" + addressId + '\'' +
				", country=" + country + '\'' +
				", city=" + city + '\'' +
				", zipCode=" + zipCode + '\'' +
				", street=" + street +
				'}';
	}
}
