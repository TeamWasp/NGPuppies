package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.User;

public interface UserService {
	
	User getByUsername(String username);
	int getIdByUsername(String username);
}
