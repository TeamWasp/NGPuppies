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




    //TODO: implement SQL querry
    public List<Subscriber> getTopTenSubscribers(int userId) {
        return null;
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




    @Override
    public void create(Client client) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Client getById(int clientId) {
        Client client = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            client = session.get(Client.class, clientId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return client;
    }
    
    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            clients = session.createQuery("from Client", Client.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return clients;
    }
    
    @Override
    public void update(int clientId, Client updateClient) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            client.setUsername(updateClient.getUsername());
            client.setPassword(updateClient.getPassword());
            client.setEik(updateClient.getEik());
            client.setEnabled(updateClient.isEnabled());
            //client.setRole(updateClient.getRole());
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void delete(int clientId) {
        Client client = getById(clientId);
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
