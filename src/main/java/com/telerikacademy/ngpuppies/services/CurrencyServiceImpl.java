package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Currency;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import com.telerikacademy.ngpuppies.services.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements GenericService<Currency> {
	
	private GenericRepository<Currency> repository;
	
	@Autowired
	public CurrencyServiceImpl(GenericRepository<Currency> repository) {
		this.repository = repository;
	}
	
	@Override
	public void create(Currency currency) {
		repository.create(currency);
	}
	
	@Override
	public Currency getById(int currencyId) {
		return repository.getById(currencyId);
	}
	
	@Override
	public List<Currency> getAll() {
		return repository.getAll();
	}
	
	@Override
	public void update(int currencyId, Currency currency) {
		repository.update(currencyId, currency);
	}
	
	@Override
	public void delete(int currencyId) { repository.delete(currencyId);	}
}
