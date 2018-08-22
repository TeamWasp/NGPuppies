package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberSqlRepository implements SubscriberRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public SubscriberSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Subscriber subscriber) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(subscriber);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Subscriber getById(String subscriberId) {
		Subscriber subscriber = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			subscriber = session.get(Subscriber.class, subscriberId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return subscriber;
	}
	
	@Override
	public List<Subscriber> getAll() {
		List<Subscriber> subscribers = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			subscribers = session.createQuery("from Subscriber", Subscriber.class).getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return subscribers;
	}
	
	@Override
	public List<Subscriber> getAll(int clientId) {
		List<Subscriber> subscribers = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Subscriber as a where a.bank = :clientId");
			query.setParameter("clientId", clientId);
			subscribers = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return subscribers;
	}
	
	@Override
	public void update(String subscriberId, Subscriber updateSubscriber) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Subscriber subscriber = session.get(Subscriber.class, subscriberId);
			subscriber.setPhoneNumber(updateSubscriber.getPhoneNumber());
			subscriber.setFirstName(updateSubscriber.getFirstName());
			subscriber.setLastName(updateSubscriber.getLastName());
			subscriber.setEgn(updateSubscriber.getEgn());
			subscriber.setAddress(updateSubscriber.getAddress());
			subscriber.setBank(updateSubscriber.getBank());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(String subscriberId) {
		Subscriber subscriber = getById(subscriberId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(subscriber);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
