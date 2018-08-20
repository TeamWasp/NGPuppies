package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.Client;

import java.util.List;

public interface ClientRepository {
	
	List<Subscriber> getAllSubscribers(int userId);
	List<Subscriber> getTopTenSubscribers(int userId);
	
}
