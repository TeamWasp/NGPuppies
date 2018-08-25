package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.*;
import com.telerikacademy.ngpuppies.repositories.base.*;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private GenericRepository<Address> addressRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminServiceImpl(
			GenericRepository<Admin> adminRepository,
			GenericRepository<com.telerikacademy.ngpuppies.models.Service> serviceRepository,
			ClientRepository clientRepository,
			GenericRepository<Currency> currencyRepository,
			BillRepository billRepository,
			SubscriberRepository subscriberRepository,
			GenericRepository<User> userRepository,
			GenericRepository<Address> addressRepository,
			PasswordEncoder passwordEncoder
	) {
		this.adminRepository = adminRepository;
		this.serviceRepository = serviceRepository;
		this.clientRepository = clientRepository;
		this.currencyRepository = currencyRepository;
		this.billRepository = billRepository;
		this.subscriberRepository = subscriberRepository;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void create(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
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
		Admin admin = adminRepository.getById(adminId);
		admin.setPassword("");
		return admin;
	}
	
	@Override
	public Client getClientById(int clientId) {
		Client client = clientRepository.getById(clientId);
		client.setPassword("");
		return client;
	}
	
	@Override
	public User getUserById(int userId) {
		User user = userRepository.getById(userId);
		user.setPassword("");
		return user;
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
		List<Admin> admins = adminRepository.getAll();
		for (Admin admin : admins)
		{
			admin.setPassword("");
		}
		
		return admins;
	}
	
	@Override
	public List<Client> getAllClients() {
		List<Client> clients = clientRepository.getAll();
		for (Client client : clients)
		{
			client.setPassword("");
		}
		
		return clients;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.getAll();
		for (User user : users)
		{
			user.setPassword("");
		}
		
		return users;
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
		Address address = subscriber.getAddress();
		subscriberRepository.update(subscriberId, subscriber);
		addressRepository.update(address.getAddressId(), address);
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
	
}
