package com.telerikacademy.ngpuppies.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWebController {
	
	@GetMapping("/admin")
	public String homePage(Model model) {
		model.addAttribute("header", "sections/site-admin-navigation");
		model.addAttribute("view", "list");
		model.addAttribute("footer", "sections/site-footer-fixed");
		return "base";
	}
	
	@GetMapping("/admin/personal")
	public String personalPage(Model model) {
		model.addAttribute("header", "sections/site-admin-navigation");
		model.addAttribute("view","sections/personal-form");
		model.addAttribute("footer","sections/site-footer-relative");
		return "base";
	}
}