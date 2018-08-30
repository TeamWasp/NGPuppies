package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.*;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.security.services.base.TokenService;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;
    private BillRepository billRepository;
    private SubscriberRepository subscriberRepository;
    private TokenService<HttpServletRequest> tokenService;

    @Autowired
    public ClientServiceImpl(
        ClientRepository repository,
        BillRepository billRepository,
        SubscriberRepository subscriberRepository,
        TokenService<HttpServletRequest> tokenService) {
            this.repository = repository;
            this.billRepository = billRepository;
            this.subscriberRepository = subscriberRepository;
            this.tokenService = tokenService;
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
    public void payBill(int billId, HttpServletRequest req) {
        String usernameFromReq = tokenService.getUsernameFromToken(req);
        String usernameFromDb = billRepository.getById(billId).getSubscriber().getBank().getUsername();
        
        if (usernameFromReq.equals(usernameFromDb)) {
            repository.payBill(billId);
        }
        else{
            System.out.printf("User is not authorized to pay bill with id: \"%d\"", billId);
        }
    }

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
