package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.repositories.base.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserSqlRepository implements UserRepository {

	private SessionFactory factory;

	@Autowired
	public UserSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void create(User user) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public User getById(int userId) {
		User user = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			user = session.get(User.class, userId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			users = session.createQuery("from users", User.class).getResultList(); // users with lower "u" so that Hibernate directly refers to db table, else (written as "User") will not find it
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return users;
	}

	@Override
	public void update(int userId, User updateUser) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			User user = session.get(User.class, userId);
			user.setUsername(updateUser.getUsername());
			user.setPassword(updateUser.getPassword());
			user.setRole(updateUser.getRole());
			user.setEnabled(updateUser.isEnabled());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void delete(int userId) {
		User user = getById(userId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public User getByUsername(String username) {
		User user = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			user = session.createQuery("from users u where u.username = :username", User.class)
					.setParameter("username",username)
					.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return user;
	}

	@Override
	public int getIdByUsername(String username) {
			int id=0;
			try (Session session = factory.openSession()) {
					session.beginTransaction();
					Query query = session.createQuery("select u.id from users u where u.username = :username")
									.setParameter("username",username);
					id = (int) query.uniqueResult();
					session.getTransaction().commit();
			} catch (Exception ex) {
					System.out.println(ex.getMessage());
			}
			return id;
	}
}