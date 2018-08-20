package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.repositories.base.AdminRepository;
import com.telerikacademy.ngpuppies.services.base.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
	
	private AdminRepository repository;
	
	public AdminServiceImpl(AdminRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void delete(int adminId) {
		repository.delete(adminId);
	}
	
	@Override
	public void create(Admin admin) {
		repository.create(admin);
	}
	
	@Override
	public Admin getById(int adminId) {
		return repository.getById(adminId);
	}
	
	@Override
	public List<Admin> getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(int adminId, Admin admin) {
		repository.update(adminId, admin);
	}
}
