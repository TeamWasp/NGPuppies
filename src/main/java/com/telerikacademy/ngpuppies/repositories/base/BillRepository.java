package com.telerikacademy.ngpuppies.repositories.base;

import com.telerikacademy.ngpuppies.models.Bill;

import java.util.Date;
import java.util.List;

public interface BillRepository extends GenericRepository<Bill> {
	
	List<Bill> getAll(Date startDate, Date endDate);
	
	List<Bill> getAll(String subscriberId);

	List<Bill> getUnpaidBills(int userId);

	List<Bill> getPaymentHistory(int userId);

}
