package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.*;
import com.telerikacademy.ngpuppies.models.dto.BillDTO;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;
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
	private CurrencyRepository currencyRepository;
	private BillRepository billRepository;
	private SubscriberRepository subscriberRepository;
	private UserRepository userRepository;
	private AddressRepository addressRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminServiceImpl(
			GenericRepository<Admin> adminRepository,
			GenericRepository<com.telerikacademy.ngpuppies.models.Service> serviceRepository,
			ClientRepository clientRepository,
			CurrencyRepository currencyRepository,
			BillRepository billRepository,
			SubscriberRepository subscriberRepository,
			UserRepository userRepository,
			AddressRepository addressRepository,
			PasswordEncoder passwordEncoder
	) {
		this.adminRepository = adminRepository;
		this.serviceRepository = serviceRepository;
		this.clientRepository = clientRepository;
		this.currencyRepository = currencyRepository;
		this.billRepository = billRepository;
		this.subscriberRepository = subscriberRepository;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void create(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setEnabled(true);
		admin.setFirstLogin(true);
		adminRepository.create(admin);
	}
	
	@Override
	public void create(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		client.setEnabled(true);
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
	public void create(BillDTO billDto) {
		int serviceId = billDto.getServiceId();
		String phoneNumber = billDto.getPhoneNumber();
		Date startDate = billDto.getStartDate();
		Date endDate = billDto.getEndDate();
		double amount = billDto.getAmount();
		String currencyName = billDto.getCurrency();
		
		com.telerikacademy.ngpuppies.models.Service serviceDb = null;
		Subscriber subscriberDb = null;
		Currency currencyDb = null;
		try {
			serviceDb = serviceRepository.getById(serviceId);
			subscriberDb = subscriberRepository.getById(phoneNumber);
			currencyDb = currencyRepository.getByName(currencyName);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		Bill bill = new Bill(serviceDb, subscriberDb, currencyDb, startDate, endDate, amount, null);
		
		billRepository.create(bill);
	}
	
	@Override
	public void create(SubscriberDTO sub) {
		
		int userId = 0;
		try {
			userId = userRepository.getIdByUsername(sub.getBank());
		} catch (Exception ex) {
			System.out.println("No such bank exists!");
		}
		
		Client bank = new Client(userId);
		
		String phoneNumber = sub.getPhoneNumber();
		String firstName = sub.getFirstName();
		String lastName = sub.getLastName();
		String egn = sub.getEgn();
		
		Address address = new Address(sub.getCountry(), sub.getCity(), sub.getZipCode(), sub.getStreet());
		if (subscriberRepository.getById(phoneNumber) != null) {
			System.out.println("Subscriber already exits!");
		}
		else {
			addressRepository.create(address);
		}
		
		int addressId = addressRepository.loadLast();
		
		Subscriber subscriber = new Subscriber(phoneNumber, firstName, lastName, egn, new Address(addressId), bank);
		try {
			subscriberRepository.create(subscriber);
		} catch (Exception ex) {
			System.out.println("Subscriber already exists!");
		}
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
	public int getIdByUsername(String username) {
		return userRepository.getIdByUsername(username);
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
	public SubscriberDTO loadSubscriberById(String subscriberId) {
		return subscriberRepository.loadById(subscriberId);
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
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		adminRepository.update(adminId, admin);
	}
	
	@Override
	public void update(int clientId, Client client) {
		if(!client.getPassword().equals("")) {
			client.setPassword(passwordEncoder.encode(client.getPassword()));
		}
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
	
	/*@Override
	public void update(int billId, Bill bill) {
		billRepository.update(billId, bill);
	}*/
	
	@Override
	public void update(String subscriberId, SubscriberDTO subscriberDto) {
		
		String phoneNumber = subscriberDto.getPhoneNumber();
		String firstName = subscriberDto.getFirstName();
		String lastName = subscriberDto.getLastName();
		String egn = subscriberDto.getEgn();
		String country = subscriberDto.getCountry();
		String city = subscriberDto.getCity();
		String zipCode = subscriberDto.getZipCode();
		String street = subscriberDto.getStreet();
		String bank = subscriberDto.getBank();
		
		Subscriber subscriberDb = subscriberRepository.getById(phoneNumber);
		int addressId = subscriberDb.getAddress().getAddressId();
		int bankId = userRepository.getIdByUsername(bank);
		
		Client user = new Client(bankId);
		Address address = new Address(addressId, country, city, zipCode, street);
		Subscriber subscriber = new Subscriber(phoneNumber, firstName, lastName, egn, address, user);
		addressRepository.update(addressId, address);
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

	public void changePassword(int adminId, String newPassword){
		Admin admin = adminRepository.getById(adminId);
		admin.setFirstLogin(false);
		admin.setPassword(passwordEncoder.encode(newPassword));
		adminRepository.update(adminId,admin);
	}

}
