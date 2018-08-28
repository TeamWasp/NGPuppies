package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Client;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import com.telerikacademy.ngpuppies.repositories.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;
    private BillRepository billRepository;
    private SubscriberRepository subscriberRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, BillRepository billRepository, SubscriberRepository subscriberRepository) {
        this.repository = repository;
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;

    }

    @Override
    public List<Subscriber> getAllSubscribers(int userId) {

        return subscriberRepository.getAll(userId);
    }

    @Override
    public List<SubscriberDTO> getTopTenSubscribers(int userId) {
        return subscriberRepository.getTopTen(userId);
    }


    @Override
    public void payBill(int billId){
        repository.payBill(billId);
    }

    /*@Override
    public List<Bill> getAllBills(int userId) {
        return this.repository.getAllBills(userId);
    }*/
    
    @Override
    public List<Bill> getAllBills(int userId) {
        List<Bill> billsClient = new ArrayList<>();
        for (Subscriber sub : subscriberRepository.getAll(userId)) {
            billsClient.addAll(sub.getBills());
        }
        
        return billsClient;
    }

    @Override
    public List<Bill> getUnpaidBills(int userId) {
        return this.billRepository.getUnpaidBills(userId);
    }
}
