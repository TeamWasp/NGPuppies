package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Admin;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Note: Here maybe we need to use User instead of Admin in order for the Admin to be able to manage not only Admin(s) but also Client(s)
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
			admin.setUsername(updateAdmin.getUsername());
			admin.setPassword(updateAdmin.getPassword());
			//admin.setRole(updateAdmin.getRole());
			admin.setEmailAddress(updateAdmin.getEmailAddress());
			admin.setEnabled(updateAdmin.isEnabled());
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
