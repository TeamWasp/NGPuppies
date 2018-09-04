package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Address;

public interface AddressRepository extends GenericRepository<Address> {
	
	int loadLast();
}
