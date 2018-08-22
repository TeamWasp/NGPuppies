package com.telerikacademy.ngpuppies.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
@DiscriminatorValue("1")
public class Admin extends User {
	
	@Email
	@Column(name = "EmailAddress")
	private String emailAddress;
	
	@Column(name = "IsNewUser")
	private boolean isNewUser;
	
	public Admin() {}
	
	public Admin(String username, String password, Role role, String eik, String emailAddress, Boolean isNewUser) {
		super(username, password, role, eik);
		this.emailAddress = emailAddress;
		this.isNewUser = isNewUser;
	}
	
	public Admin(String username, String password, String eik, String emailAddress, Boolean isNewUser) {
		super(username, password, eik);
		this.emailAddress = emailAddress;
		this.isNewUser = isNewUser;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public boolean getIsNewUser() {
		return isNewUser;
	}
	
	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
}
