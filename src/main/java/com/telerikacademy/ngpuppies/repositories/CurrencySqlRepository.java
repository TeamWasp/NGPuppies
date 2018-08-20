package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Currency;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencySqlRepository implements GenericRepository<Currency> {
	
	private SessionFactory factory;
	
	@Autowired
	public CurrencySqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Currency currency) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(currency);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Currency getById(int currencyId) {
		Currency currency = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			currency = session.get(Currency.class, currencyId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return currency;
	}
	
	@Override
	public List<Currency> getAll() {
		List<Currency> currencies = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			currencies = session.createQuery("from Currency").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return currencies;
	}
	
	@Override
	public void update(int currencyId, Currency updateCurrency) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Currency currency = session.get(Currency.class, currencyId);
			currency.setCurrency(updateCurrency.getCurrency());
			currency.setExchangeRate(updateCurrency.getExchangeRate());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int currencyId) {
		Currency currency = getById(currencyId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(currency);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
