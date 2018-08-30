package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.UserSqlRepository;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import com.telerikacademy.ngpuppies.repositories.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.security.JwtTokenUtil;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.ngpuppies.security.Constants.HEADER_STRING;
import static com.telerikacademy.ngpuppies.security.Constants.TOKEN_PREFIX;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;
    private BillRepository billRepository;
    private SubscriberRepository subscriberRepository;
    private UserSqlRepository userSqlRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, BillRepository billRepository, SubscriberRepository subscriberRepository, UserSqlRepository userSqlRepository) {
        this.repository = repository;
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
        this.userSqlRepository = userSqlRepository;

    }

    @Override
    //TODO: IMPLEMENT THIS AUTHENTICATION FOR ALL METHODS!
    //TODO: Think of a way to implement once instead of copypasting into every method
    public List<Subscriber> getAllSubscribers(int userId, HttpServletRequest req) {
        String header = req.getHeader(HEADER_STRING);
        String authToken = null;
        String username = null;
        if (header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            String authUsername = userSqlRepository.getById(userId).getUsername();
            if(username.equals(authUsername)){
                return subscriberRepository.getAll(userId);
            }
            else{
                return null;
            }

        }
        return null;
    }

    @Override
    public List<SubscriberDTO> getTopTenSubscribers(int userId) {
        return subscriberRepository.getTopTen(userId);
    }


    @Override
    public void payBill(int billId) {
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
