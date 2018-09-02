package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminSqlRepository implements GenericRepository<Admin> {
	
	private SessionFactory factory;
	
	@Autowired
	public AdminSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Admin admin) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(admin);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Admin getById(int adminId) {
		Admin admin = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			admin = session.get(Admin.class, adminId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return admin;
	}
	
	@Override
	public List<Admin> getAll() {
		List<Admin> admins = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			admins = session.createQuery("from Admin", Admin.class).getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return admins;
	}
	
	@Override
	public void update(int adminId, Admin updateAdmin) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Admin admin = session.get(Admin.class, adminId);
			if (updateAdmin.getUsername() != null) {
				admin.setUsername(updateAdmin.getUsername());
			}
			if (updateAdmin.getPassword() != null) {
				admin.setPassword(updateAdmin.getPassword());
			}
			if (updateAdmin.getEmailAddress() != null) {
				admin.setEmailAddress(updateAdmin.getEmailAddress());
			}
			if (updateAdmin.isEnabled() && updateAdmin.isEnabled() != admin.isEnabled()) {
				admin.setEnabled(updateAdmin.isEnabled());
			}
			if (admin.isFirstLogin()){
				admin.setFirstLogin(updateAdmin.isFirstLogin());
			}
			//admin.setRole(updateAdmin.getRole());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int adminId) {
		Admin admin = getById(adminId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(admin);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
