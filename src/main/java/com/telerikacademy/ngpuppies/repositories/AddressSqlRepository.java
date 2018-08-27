package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Address;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressSqlRepository implements GenericRepository<Address> {
	private SessionFactory factory;
	
	@Autowired
	public AddressSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Address address) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(address);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Address getById(int addressId) {
		Address address = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			address = session.get(Address.class, addressId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return address;
	}
	
	@Override
	public List<Address> getAll() {
		List<Address> addresses = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			addresses = session.createQuery("from Address ", Address.class).getResultList(); // users with lower "u" so that Hibernate directly refers to db table, else (written as "User") will not find it
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return addresses;
	}
	
	@Override
	public void update(int addressId, Address updateAddress) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Address user = session.get(Address.class, addressId);
			user.setCountry(updateAddress.getCountry());
			user.setCity(updateAddress.getCity());
			user.setZipCode(updateAddress.getZipCode());
			user.setStreet(updateAddress.getStreet());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int addressId) {
		Address address = getById(addressId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(address);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
