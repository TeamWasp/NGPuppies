package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.dto.SubscriberDTO;

import java.util.List;

public interface ClientService {
    List<Subscriber> getAllSubscribers(int userId);
    List<SubscriberDTO> getTopTenSubscribers(int userId);
    void payBill(int billId);
    List<Bill> getAllBills(int userId);
    List<Bill> getUnpaidBills(int userId);
}
