package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Service;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceSqlRepository implements GenericRepository<Service> {
	
	private SessionFactory factory;
	
	@Autowired
	public ServiceSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Service service) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(service);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Service getById(int serviceId) {
		Service service = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			service = session.get(Service.class, serviceId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return service;
	}
	
	@Override
	public List<Service> getAll() {
		List<Service> services = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			services = session.createQuery("from Service").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return services;
	}
	
	@Override
	public void update(int adminId, Service updateService) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Service service = session.get(Service.class, adminId);
			service.setName(updateService.getName());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int serviceId) {
		Service service = getById(serviceId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(service);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
