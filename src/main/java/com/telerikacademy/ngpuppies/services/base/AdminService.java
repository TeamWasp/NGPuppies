package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.*;

import java.util.Date;
import java.util.List;

public interface AdminService {
	
	// Create methods
	void create(Admin admin); //throws Exception;
	
	void create(Client client);
	
	//void create(User user);
	
	void create(Service service);
	
	void create(Currency currency);
	
	void create(Bill bill);
	
	void create(Subscriber subscriber);
	
	// getById methods
	Admin getAdminById(int adminId);
	
	Client getClientById(int clientId);
	
	User getUserById(int userId);
	
	Service getServiceById(int serviceId);
	
	Currency getCurrencyById(int currencyId);
	
	Bill getBillById(int billId);
	
	Subscriber getSubscriberById(String subscriberId);
	
	// getAll methods
	List<Admin> getAllAdmins();
	
	List<Client> getAllClients();
	
	List<User> getAllUsers();
	
	List<Service> getAllServices();
	
	List<Currency> getAllCurrencies();
	
	List<Bill> getAllBills();
	
	List<Bill> getAllBills(Date startDate, Date endDate);
	
	List<Subscriber> getAllSubscribers();
	
	List<Subscriber> getAllSubscribers(int clientId);
	
	// update methods
	void update(int adminId, Admin admin);
	
	void update(int clientId, Client client);
	
	void update(int serviceId, Service service);
	
	void update(int currencyId, Currency currency);
	
	void update(int billId, Bill bill);
	
	void update(String subscriberId, Subscriber subscriber);
	
	// delete methods
	void deleteAdmin(int adminId);
	
	void deleteClient(int clientId);
	
	void deleteUser(int userId);
	
	void deleteService(int serviceId);
	
	void deleteCurrency(int currencyId);
	
	void deleteBill(int billId);
	
	void deleteSubscriber(String subscriberId);
}
