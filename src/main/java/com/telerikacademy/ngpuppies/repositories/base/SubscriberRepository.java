package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Subscriber;

import java.util.List;

public interface SubscriberRepository {
	
	void create(Subscriber subscriber);
	
	Subscriber getById(String subscriberId);
	
	List<Subscriber> getAll();
	
	// get all by bank
	List<Subscriber> getAll(int clientId);
	
	void update(String subscriberId, Subscriber subscriber);
	
	void delete(String subscriberId);
}
