package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Currency;

public interface CurrencyRepository extends GenericRepository<Currency> {
	
	Currency getByName(String name);
}
