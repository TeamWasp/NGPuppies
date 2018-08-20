package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Subscriber;

import java.util.List;

public interface SubscriberRepository extends GenericRepository<Subscriber> {
	
	List<Subscriber> getAll(int clientId);
}
