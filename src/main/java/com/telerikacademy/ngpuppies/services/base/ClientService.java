package com.telerikacademy.ngpuppies.services.base;

import com.mchange.rmi.NotAuthorizedException;
import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.dto.BillDTO;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ClientService {
    Subscriber getSubscriber(String id, HttpServletRequest req) throws NotAuthorizedException;

    List<Subscriber> getAllSubscribers(int userId);

    List<SubscriberDTO> getTopTenSubscribers(int userId);

    void payBill(int billId, HttpServletRequest req) throws NotAuthorizedException;

    List<Bill> getAllBills(int userId);

    List<Bill> getUnpaidBills(int userId);

    List<Bill> getPaymentHistory(int userId);

    List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> dates, String phoneNumber, int userId);

    void payMultipleBills(List<String> bills, HttpServletRequest req);
}
