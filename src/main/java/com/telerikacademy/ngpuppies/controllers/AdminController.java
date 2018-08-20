package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
	private AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public List<Admin> getAllAdmins() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable("id") String adminIdString) {
		int adminId = Integer.parseInt(adminIdString);
		return service.getById(adminId);
	}
	
	@PostMapping("/createAdmin")
	public void createAdmin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "eik") String eik,
			@RequestParam(value = "emailAddress") String emailAddress) {
		
		Admin newAdmin = new Admin(username, password, eik, emailAddress);
		service.create(newAdmin);
	}
	
	@PutMapping("/updateAdmin/{id}")
	public void updateAdmin(
			@PathVariable("id") String adminIdString,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "eik", required = false) String eik,
			@RequestParam(value = "emailAddress", required = false) String emailAddress){
		int adminId = Integer.parseInt(adminIdString);
		Admin updatedAdmin = new Admin(username, password, eik, emailAddress);
		service.update(adminId, updatedAdmin);
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	public void deleteAdmin(@PathVariable("id") String adminIdString) {
		int adminId = Integer.parseInt(adminIdString);
		service.delete(adminId);
	}
}
