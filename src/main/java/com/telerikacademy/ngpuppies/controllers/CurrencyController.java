package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Currency;
import com.telerikacademy.ngpuppies.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	private GenericService<Currency> service;
	
	@Autowired
	public CurrencyController(GenericService<Currency> service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public List<Currency> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Currency getById(@PathVariable("id") String idString) {
		int id = Integer.parseInt(idString);
		return service.getById(id);
	}
}
