package com.telerikacademy.ngpuppies.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Client extends User {
	
	@OneToMany(mappedBy = "bank")
	private List<Subscriber> subscribers;
	
	public Client() {}
	
	public Client(String username, String password, Role role, String eik) {
		super(username, password, role, eik);
	}
	
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
}
