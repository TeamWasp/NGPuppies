package com.telerikacademy.ngpuppies.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginWebController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "error/access-denied";
	}
}