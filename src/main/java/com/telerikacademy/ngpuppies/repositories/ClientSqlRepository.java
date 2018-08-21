package com.telerikacademy.ngpuppies.repositories;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Client;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClientSqlRepository implements ClientRepository {
    private SessionFactory factory;

    @Autowired
    public ClientSqlRepository(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public List<Subscriber> getAllSubscribers(int userId) {
        List<Subscriber> subscribers = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Client c = session.get(Client.class, userId);
            subscribers = c.getSubscribers();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return subscribers;
    }

    @Override
    public List<Subscriber> getTopTenSubscribers(int userId) {
        List<Subscriber> subscribers = getAllSubscribers(userId);
        Collections.sort(subscribers);
        return subscribers.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public void payBill(int billId) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Bill b = session.get(Bill.class, billId);
            b.setPaymentDate(new Date());
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
