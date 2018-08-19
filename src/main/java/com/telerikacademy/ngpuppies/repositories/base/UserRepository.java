package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {
	
	List<Subscriber> listAllSubscribers(int userId);
	
}
