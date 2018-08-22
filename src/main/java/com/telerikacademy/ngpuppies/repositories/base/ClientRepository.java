package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.Client;

import java.util.List;

public interface ClientRepository extends GenericRepository<Client> {
	
	List<Subscriber> getAllSubscribers(int userId);
	List<Subscriber> getTopTenSubscribers(int userId);
	void payBill(int billId);
	List<Bill> getAllBills(int userId);
	List<Bill> getUnpaidBills(int userId);


}
