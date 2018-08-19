package com.telerikacademy.ngpuppies.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class User extends Client {
	
	@OneToMany(mappedBy = "bank")
	private List<Subscriber> subscribers;
	
	public User() {}
	
	public User(int userId, String username, String password, Role role, String eik) {
		super(userId, username, password, role, eik);
	}
	
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
}
