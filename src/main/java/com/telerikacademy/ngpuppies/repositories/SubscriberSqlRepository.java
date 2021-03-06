package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Client;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
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
	public SubscriberDTO loadById(String subscriberId) {
		SubscriberDTO subscriberDTO = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			String query = "select b.phoneNumber as phoneNumber, " +
					"b.firstName as firstName, " +
					"b.lastName as lastName, " +
					"b.egn as egn, " +
					"b.address.country as country, " +
					"b.address.city as city, " +
					"b.address.zipCode as zipCode, " +
					"b.address.street as street, " +
					"b.bank.username as bank " +
					"from Subscriber as b " +
					"where b.phoneNumber = :subscriberId";
			subscriberDTO = (SubscriberDTO) session.createQuery(query)
					.setParameter("subscriberId", subscriberId)
					.setResultTransformer(Transformers.aliasToBean(SubscriberDTO.class))
					.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return subscriberDTO;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subscriber> getAll(int clientId) {
		List<Subscriber> subscribers = new ArrayList<>();
		Client client = new Client(clientId	);
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Subscriber as a where a.bank = :clientId");
			query.setParameter("clientId", client);
			subscribers = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return subscribers;
	}
	
	@Override
	public List<SubscriberDTO> getTopTen(int clientId){
		List<SubscriberDTO> subscribers = new ArrayList<>();
		try (Session session = factory.openSession()){
			session.beginTransaction();
			String query = "select b.subscriber.phoneNumber as phoneNumber, b.subscriber.firstName as firstName, b.subscriber.lastName as lastName, " +
					"sum(b.amount*(case when b.currency.currency != 'BGN'" +
					"then  b.currency.exchangeRate else 1 end)) as sumAmount "+
					"from Bill as b " +
					"where b.subscriber.bank.userId = :bankId and b.paymentDate is Not NULL "+
					"group by b.subscriber "+
					"order by sumAmount DESC";
			subscribers = session.createQuery(query)
					.setParameter("bankId",clientId)
					.setMaxResults(10)
					.setResultTransformer(Transformers.aliasToBean(SubscriberDTO.class))
					.list();
			session.getTransaction().commit();
		}
		catch (Exception ex){
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