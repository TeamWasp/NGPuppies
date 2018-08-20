package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.Client;

import java.util.List;

public interface UserRepository extends GenericRepository<Client> {
	
	List<Subscriber> listAllSubscribers(int userId);
	
}
