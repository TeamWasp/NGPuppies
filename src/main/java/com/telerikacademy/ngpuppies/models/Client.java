package com.telerikacademy.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@DiscriminatorValue("2")
//@JsonSerialize(using= ClientSerializer.class)
public class Client extends User {
	
	@Size(min=10, max = 15)
	@Column(name = "EIK", unique = true)
	private String eik;
	
	@OneToMany(mappedBy = "bank",fetch = FetchType.EAGER)
	@JsonIgnore // Prevents error messages when updating client from REST API
	//@JsonBackReference // Prevents from infinite loops in Json (one class calls a second and the second calls the first infinitely)
	private List<Subscriber> subscribers;
	
	public Client() {}
	
	public Client(int userId) { super(userId);}
	public Client(String username, String password, Role role, boolean enabled, String eik) {
		super(username, password, role, enabled);
		this.eik = eik;
	}
	
	public Client(String username, String password, boolean enabled, String eik) {
		super(username, password, enabled);
		this.eik = eik;
	}
	
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
	
	
	public String getEik() {
		return eik;
	}
	
	public void setEik(String eik) {
		this.eik = eik;
	}
	
	@Override
	public String toString() {
		return "Client{" +
				"userId='" + getUserId() + '\'' +
				", username=" + getUsername() + '\'' +
				", password=" + getPassword() + '\'' +
				", roleId=" + getRole() + '\'' +
				", enabled=" + isEnabled() + '\'' +
				", EIK=" + getEik() +
				'}';
	}
}
