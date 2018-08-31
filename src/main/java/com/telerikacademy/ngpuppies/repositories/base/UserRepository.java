package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.User;

public interface UserRepository extends GenericRepository<User>{
	User getByUsername(String username);
	int getIdByUsername(String username);
}
