package com.telerikacademy.ngpuppies.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientWebController {
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("header", "sections/site-client-navigation");
		model.addAttribute("view", "list");
		model.addAttribute("footer", "sections/site-footer-fixed");
		return "base";
	}
}
