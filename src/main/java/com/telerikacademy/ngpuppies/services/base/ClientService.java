package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.dto.SubscriberDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClientService {
    List<Subscriber> getAllSubscribers(int userId);
    List<SubscriberDTO> getTopTenSubscribers(int userId);
    void payBill(int billId, HttpServletRequest req);
    List<Bill> getAllBills(int userId);
    List<Bill> getUnpaidBills(int userId);
    int getIdFromToken(HttpServletRequest req);
}
