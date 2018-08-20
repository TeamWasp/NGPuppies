package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.repositories.base.AdminRepository;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository repository;
	
	public AdminServiceImpl(AdminRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void delete(int adminId) {
		Admin admin = getById(adminId);
		if(admin == null){
			System.out.printf("Admin with \"%d\" does not exist!", adminId);
		}
		else {
			repository.delete(adminId);
		}
	}
	
	// cannot check if username of Client object does not match username of Admin => try to create UserService for such scenarios and transfer such functionality there
	// Or leave this for the database to handle as a duplicate key error? and use Exception Handler to process it as a message to the UI???
	@Override
	public void create(Admin admin) {
		List<Admin> allAdmins = repository.getAll();
		if (admin != null) {
			for (Admin a : allAdmins) {
				if (a.getUsername().equals(admin.getUsername())) {
					System.out.printf("Admin with username: \"%s\" already exits!\n", admin.getUsername());
					return;
				}
			}
		}
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
		Admin oldAdmin = repository.getById(adminId);
		if(admin.getUsername().equals("")){
			admin.setUsername(oldAdmin.getUsername());
		}
		
		if(admin.getPassword().equals("")){
			admin.setPassword(oldAdmin.getPassword());
		}
		
		if(admin.getEik().equals("")){
			admin.setEik(oldAdmin.getEik());
		}
		
		if(admin.getEmailAddress().equals("")){
			admin.setEmailAddress(oldAdmin.getEmailAddress());
		}
		repository.update(adminId, admin);
	}
}
