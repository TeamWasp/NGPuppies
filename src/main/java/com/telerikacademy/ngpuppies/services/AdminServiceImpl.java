package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.*;
import com.telerikacademy.ngpuppies.repositories.base.BillRepository;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.repositories.base.GenericRepository;
import com.telerikacademy.ngpuppies.repositories.base.SubscriberRepository;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	
	private GenericRepository<Admin> adminRepository;
	private GenericRepository<com.telerikacademy.ngpuppies.models.Service> serviceRepository;
	private ClientRepository clientRepository;
	private GenericRepository<Currency> currencyRepository;
	private BillRepository billRepository;
	private SubscriberRepository subscriberRepository;
	private GenericRepository<User> userRepository;
	
	
	@Autowired
	public AdminServiceImpl(
			GenericRepository<Admin> adminRepository,
			GenericRepository<com.telerikacademy.ngpuppies.models.Service> serviceRepository,
			ClientRepository clientRepository,
			GenericRepository<Currency> currencyRepository,
			BillRepository billRepository,
			SubscriberRepository subscriberRepository,
			GenericRepository<User> userRepository
	) {
		this.adminRepository = adminRepository;
		this.serviceRepository = serviceRepository;
		this.clientRepository = clientRepository;
		this.currencyRepository = currencyRepository;
		this.billRepository = billRepository;
		this.subscriberRepository = subscriberRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void create(Admin admin) {
		adminRepository.create(admin);
	}
	
	@Override
	public void create(Client client) {
		clientRepository.create(client);
	}
	
	@Override
	public void create(com.telerikacademy.ngpuppies.models.Service service) {
		serviceRepository.create(service);
	}
	
	@Override
	public void create(Currency currency) {
		currencyRepository.create(currency);
	}
	
	@Override
	public void create(Bill bill) {
		billRepository.create(bill);
	}
	
	@Override
	public void create(Subscriber subscriber) {
		subscriberRepository.create(subscriber);
	}
	
	@Override
	public Admin getAdminById(int adminId) {
		return adminRepository.getById(adminId);
	}
	
	@Override
	public Client getClientById(int clientId) {
		return clientRepository.getById(clientId);
	}
	
	@Override
	public User getUserById(int userId) {
		return userRepository.getById(userId);
	}
	
	@Override
	public com.telerikacademy.ngpuppies.models.Service getServiceById(int serviceId) {
		return serviceRepository.getById(serviceId);
	}
	
	@Override
	public Currency getCurrencyById(int currencyId) {
		return currencyRepository.getById(currencyId);
	}
	
	@Override
	public Bill getBillById(int billId) {
		return billRepository.getById(billId);
	}
	
	@Override
	public Subscriber getSubscriberById(String subscriberId) {
		return subscriberRepository.getById(subscriberId);
	}
	
	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.getAll();
	}
	
	@Override
	public List<Client> getAllClients() {
		return clientRepository.getAll();
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.getAll();
	}
	
	@Override
	public List<com.telerikacademy.ngpuppies.models.Service> getAllServices() {
		return serviceRepository.getAll();
	}
	
	@Override
	public List<Currency> getAllCurrencies() {
		return currencyRepository.getAll();
	}
	
	@Override
	public List<Bill> getAllBills() {
		return billRepository.getAll();
	}
	
	@Override
	public List<Bill> getAllBills(Date startDate, Date endDate) {
		return billRepository.getAll(startDate, endDate);
	}
	
	@Override
	public List<Subscriber> getAllSubscribers() {
		return subscriberRepository.getAll();
	}
	
	@Override
	public List<Subscriber> getAllSubscribers(int clientId) {
		return subscriberRepository.getAll(clientId);
	}
	
	@Override
	public void update(int adminId, Admin admin) {
		adminRepository.update(adminId, admin);
	}
	
	@Override
	public void update(int clientId, Client client) {
		clientRepository.update(clientId, client);
	}
	
	@Override
	public void update(int serviceId, com.telerikacademy.ngpuppies.models.Service service) {
		serviceRepository.update(serviceId, service);
	}
	
	@Override
	public void update(int currencyId, Currency currency) {
		currencyRepository.update(currencyId, currency);
	}
	
	@Override
	public void update(int billId, Bill bill) {
		billRepository.update(billId, bill);
	}
	
	@Override
	public void update(String subscriberId, Subscriber subscriber) {
		subscriberRepository.update(subscriberId, subscriber);
	}
	
	@Override
	public void deleteAdmin(int adminId) {
		adminRepository.delete(adminId);
	}
	
	@Override
	public void deleteClient(int clientId) {
		clientRepository.delete(clientId);
	}
	
	@Override
	public void deleteUser(int userId) {
		userRepository.delete(userId);
	}
	
	@Override
	public void deleteService(int serviceId) {
		serviceRepository.delete(serviceId);
	}
	
	@Override
	public void deleteCurrency(int currencyId) {
		currencyRepository.delete(currencyId);
	}
	
	@Override
	public void deleteBill(int billId) {
		billRepository.delete(billId);
	}
	
	@Override
	public void deleteSubscriber(String subscriberId) {
		subscriberRepository.delete(subscriberId);
	}
	
	/*@Override
	public void delete(int adminId) {
		Admin admin = getById(adminId);
		if(admin == null){
			System.out.printf("Admin with \"%d\" does not exist!", adminId);
		}
		else {
			adminRepository.delete(adminId);
		}
	}
	
	// cannot check if username of Client object does not match username of Admin => try to create UserService for such scenarios and transfer such functionality there
	// Or leave this for the database to handle as a duplicate key error? and use Exception Handler to process it as a message to the UI???
	@Override
	public void create(Admin admin) {
		List<Admin> allAdmins = adminRepository.getAll();
		if (admin != null) {
			for (Admin a : allAdmins) {
				if (a.getUsername().equals(admin.getUsername())) {
					System.out.printf("Admin with username: \"%s\" already exits!\n", admin.getUsername());
					return;
				}
			}
		}
		adminRepository.create(admin);
	}
	
	@Override
	public Admin getById(int adminId) {
		return adminRepository.getById(adminId);
	}
	
	@Override
	public List<Admin> getAll() {
		return adminRepository.getAll();
	}
	
	@Override
	public void update(int adminId, Admin admin) {
		Admin oldAdmin = adminRepository.getById(adminId);
		if(admin.getUsername().equals("")){
			admin.setUsername(oldAdmin.getUsername());
		}
		
		if(admin.getPassword().equals("")){
			admin.setPassword(oldAdmin.getPassword());
		}
		
		if(admin.getEik().equals("")){
			admin.setEik(oldAdmin.getEik());
		}
		
		if(admin.getEmailAddress().equals("")){
			admin.setEmailAddress(oldAdmin.getEmailAddress());
		}
		adminRepository.update(adminId, admin);
	}*/
}
