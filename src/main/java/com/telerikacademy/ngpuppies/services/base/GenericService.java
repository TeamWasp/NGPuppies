package com.telerikacademy.ngpuppies.services.base;

import java.util.List;

public interface GenericService<T> {
	
	void create(T obj); //throws Exception;
	
	T getById(int id);
	
	List<T> getAll();
	
	void update(int id, T obj);
	
	void delete(int id);
}
