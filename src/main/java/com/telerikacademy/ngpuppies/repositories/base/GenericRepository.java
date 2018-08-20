package com.telerikacademy.ngpuppies.repositories.base;

import java.util.List;

public interface GenericRepository<T> {
	
	void create(T obj);
	
	T getById(int id);
	
	List<T> getAll();
	
	void update(int id, T obj);
	
	void delete(int id);
}
