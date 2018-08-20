package com.telerikacademy.ngpuppies.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
@DiscriminatorValue("1")
public class Admin extends Client {
	
	@Email
	@Column(name ="EmailAddress")
	private String emailAddress;
	
	public Admin() {}
	
	public Admin(String username, String password, Role role, String eik, String emailAddress) {
		super(username, password, role, eik);
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
