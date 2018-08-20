package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BillSqlRepository implements GenericRepository<Bill> {
	private SessionFactory factory;
	
	@Autowired
	public BillSqlRepository(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void create(Bill bill) {
		try(Session session = factory.openSession()) {
			session.beginTransaction();
			session.save(bill);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public Bill getById(int billId) {
		Bill bill = null;
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			bill = session.get(Bill.class, billId);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bill;
	}
	
	@Override
	public List<Bill> getAll() {
		List<Bill> bills = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			bills = session.createQuery("from Bill").list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
	}
	
	@Override
	public void update(int billId, Bill updateBill) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Bill bill = session.get(Bill.class, billId);
			bill.setService(updateBill.getService());
			bill.setSubscriber(updateBill.getSubscriber());
			bill.setAmount(updateBill.getAmount());
			bill.setCurrency(updateBill.getCurrency());
			bill.setStartDate(updateBill.getStartDate());
			bill.setEndDate(updateBill.getEndDate());
			bill.setPaymentDate(updateBill.getPaymentDate());
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void delete(int billId) {
		Bill bill = getById(billId);
		try (Session session = factory.openSession()){
			session.beginTransaction();
			session.delete(bill);
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
