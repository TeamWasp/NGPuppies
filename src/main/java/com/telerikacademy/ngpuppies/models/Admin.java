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

	
	public Admin() {}
	
	public Admin(String username, String password, Role role, Boolean enabled, String emailAddress) {
		super(username, password, role, enabled);
		this.emailAddress = emailAddress;
	}
	
	public Admin(String username, String password, Boolean enabled, String emailAddress) {
		super(username, password, enabled);
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Override
	public String toString() {
		return "Admin{" +
				"userId='" + getUserId() + '\'' +
				", username=" + getUsername() + '\'' +
				", password=" + getPassword() + '\'' +
				", roleId=" + getRole() + '\'' +
				", enabled=" + isEnabled() + '\'' +
				", emailAddress=" + emailAddress +
				'}';
	}
}
