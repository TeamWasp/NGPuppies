package com.telerikacademy.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Client extends User {
	
	@OneToMany(mappedBy = "bank",fetch = FetchType.EAGER)
	@JsonBackReference // Prevents from infinite loops in Json (one class calls a second and the second calls the first infinitely)
	private List<Subscriber> subscribers;
	
	public Client() {}
	
	public Client(int userId) { super(userId);}
	public Client(String username, String password, Role role, String eik, boolean isEnabled) {
		super(username, password, role, eik, isEnabled);
	}
	
	public Client(String username, String password, String eik, boolean isEnabled) {
		super(username, password, eik, isEnabled);
	}
	
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
}
