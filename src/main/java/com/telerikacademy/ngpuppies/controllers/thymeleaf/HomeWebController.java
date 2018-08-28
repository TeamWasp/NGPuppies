package com.telerikacademy.ngpuppies.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
}
