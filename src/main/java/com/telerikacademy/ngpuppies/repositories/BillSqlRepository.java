package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BillSqlRepository implements BillRepository {
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
			bills = session.createQuery("from Bill", Bill.class).getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
	}

	@Override
	public List<Bill> getAll(Date startDate, Date endDate) {
		List<Bill> bills = new ArrayList<>();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Bill as a where a.startDate > :startDate and a.endDate < :endDate", Bill.class);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			bills = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
	}

	@Override
	public List<Bill> getAll(String subscriberId) {
		List<Bill> bills = new ArrayList<>();
		Subscriber subscriber = new Subscriber(subscriberId);
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Bill as a where a.subscriber = :subscriber", Bill.class);
			query.setParameter("subscriber", subscriber);
			bills = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return bills;
	}


	@Override
    @SuppressWarnings("unchecked")
    public List<Bill> getUnpaidBills(int userId) {
        List<Bill> bills = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            String query = "select b from Bill as b " +
                    "join Subscriber as s on b.subscriber.phoneNumber = s.phoneNumber " +
                    "join Client as c on s.bank.userId = c.userId " +
                    "where s.bank.userId = :bankId and b.paymentDate is NULL";
            bills = session.createQuery(query)
                    .setParameter("bankId", userId)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
