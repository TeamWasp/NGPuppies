package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Admin;

public interface AdminRepository extends GenericRepository<Admin> {
	
	Admin getAdminByUsername(String username);
}
