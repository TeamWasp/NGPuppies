package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Service;

public interface ServiceRepository extends GenericRepository<Service> {
	
	Service getByName(String name);
}
