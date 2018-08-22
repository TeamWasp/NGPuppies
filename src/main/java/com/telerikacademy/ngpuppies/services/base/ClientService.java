package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;

import java.util.List;

public interface ClientService {
    List<Subscriber> getAllSubscribers(int userId);
    List<Subscriber> getTopTenSubscribers(int userId);
    void payBill(int billId);
    List<Bill> getAllBills(int userId);
    List<Bill> getUnpaidBills(int userId);
}
