package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.*;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {
	private AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}
	
	// admins requests
	@GetMapping("/admins/")
	public List<Admin> getAllAdmins() {
		return service.getAllAdmins();
	}
	
	@GetMapping("/admins/{id}")
	public Admin getAdminById(@PathVariable("id") String adminIdString) {
		int adminId = Integer.parseInt(adminIdString);
		return service.getAdminById(adminId);
	}
	
	@PostMapping("/admins/")
	public void createAdmin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "eik") String eik,
			@RequestParam(value = "emailAddress") String emailAddress) {
		
		boolean enabled = false;
		
		Admin newAdmin = new Admin(username, password, enabled, emailAddress);
		service.create(newAdmin);
	}
	
	@PutMapping("/admins/{id}")
	public void updateAdmin(
			@PathVariable("id") String adminIdString,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "eik", required = false) String eik,
			@RequestParam(value = "emailAddress", required = false) String emailAddress,
			@RequestParam(value = "enabled", required = false) String enabledString){
		int adminId = Integer.parseInt(adminIdString);
		boolean enabled = true;
		if(enabledString.equals("0")){
			enabled = false;
		}
		Admin updatedAdmin = new Admin(username, password, enabled, emailAddress);
		service.update(adminId, updatedAdmin);
	}
	
	@DeleteMapping("/admins/{id}")
	public void deleteAdmin(@PathVariable("id") String adminIdString) {
		int adminId = Integer.parseInt(adminIdString);
		service.deleteAdmin(adminId);
	}
	
	// users requests
	@GetMapping("/users/")
	public List<User> getAllUsers() { return service.getAllUsers(); }
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		return service.getUserById(userId);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") String userIdString) {
		int userId = Integer.parseInt(userIdString);
		service.deleteUser(userId);
	}
	
	// clients requests
	@GetMapping("/clients/")
	public List<Client> getAllClients() {
		return service.getAllClients();
	}
	
	@GetMapping("/clients/{id}")
	public Client getClientById(@PathVariable("id") String clientIdString) {
		int clientId = Integer.parseInt(clientIdString);
		return service.getClientById(clientId);
	}
	
	@PostMapping("/clients/")
	public void createClient(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "eik") String eik) {
		
		boolean isEnabled = true;
		Client newClient = new Client(username, password, isEnabled, eik);
		service.create(newClient);
	}
	
	@PutMapping("/clients/{id}")
	public void updateClient(
			@PathVariable("id") String clientIdString,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "eik", required = false) String eik,
			@RequestParam(value = "enabled", required = false) String enabledString){
		
		boolean enabled = true;
		if (enabledString.equals("false")) {
			enabled = false;
		}
		int clientId = Integer.parseInt(clientIdString);
		
		Client updateClient = new Client(username, password, enabled, eik);
		service.update(clientId, updateClient);
	}
	
	@DeleteMapping("/clients/{id}")
	public void deleteClient(@PathVariable("id") String clientIdString) {
		int clientId = Integer.parseInt(clientIdString);
		service.deleteClient(clientId);
	}
	
	// service requests
	@GetMapping("/services/")
	public List<Service> getAllServices() {
		return service.getAllServices();
	}
	
	@GetMapping("/services/{id}")
	public Service getServiceById(@PathVariable("id") String serviceIdString) {
		int serviceId = Integer.parseInt(serviceIdString);
		return service.getServiceById(serviceId);
	}
	
	@PostMapping("/services/")
	public void createService(
			@RequestParam(value = "name") String name) {
		
		Service newService = new Service(name);
		service.create(newService);
	}
	
	@PutMapping("/services/{id}")
	public void updateService(
			@PathVariable("id") String serviceIdString,
			@RequestParam(value = "name", required = false) String name){
		
		int serviceId = Integer.parseInt(serviceIdString);
		
		Service updateService = new Service(name);
		service.update(serviceId, updateService);
	}
	
	@DeleteMapping("/services/{id}")
	public void deleteService(@PathVariable("id") String serviceIdString) {
		int serviceId = Integer.parseInt(serviceIdString);
		service.deleteService(serviceId);
	}
	
	// currency requests
	@GetMapping("/currencies/")
	public List<Currency> getAllCurrencies() {
		return service.getAllCurrencies();
	}
	
	@GetMapping("/currencies/{id}")
	public Currency getCurrencyById(@PathVariable("id") String currencyIdString) {
		int currencyId = Integer.parseInt(currencyIdString);
		return service.getCurrencyById(currencyId);
	}
	
	@PostMapping("/currencies/")
	public void createCurrency(
			@RequestParam(value = "currency") String currency,
			@RequestParam(value = "exchangeRate") String exchangeRateString) {
		
		double exchangeRate = Double.parseDouble(exchangeRateString);
		Currency newCurrency = new Currency(currency, exchangeRate);
		service.create(newCurrency);
	}
	
	@PutMapping("/currencies/{id}")
	public void updateCurrency(
			@PathVariable("id") String currencyIdString,
			@RequestParam(value = "currency", required = false) String currency,
			@RequestParam(value = "exchangeRate", required = false) String exchangeRateString){
		
		int currencyId = Integer.parseInt(currencyIdString);
		double exchangeRate = Double.parseDouble(exchangeRateString);
		
		Currency updateCurrency = new Currency(currency, exchangeRate);
		service.update(currencyId, updateCurrency);
	}
	
	@DeleteMapping("/currencies/{id}")
	public void deleteCurrency(@PathVariable("id") String currencyIdString) {
		int currencyId = Integer.parseInt(currencyIdString);
		service.deleteCurrency(currencyId);
	}
	
	// subscriber requests
	@GetMapping("/subscribers/")
	public List<Subscriber> getAllSubscribers() {
		return service.getAllSubscribers();
	}
	
	@GetMapping("/subscribers")
	public List<Subscriber> getAllSubscribersByBankId(
			@RequestParam(value = "bankId") String bankIdString)
	{
		int bankId = Integer.parseInt(bankIdString);
		return service.getAllSubscribers(bankId);
	}
	
	@GetMapping("/subscribers/{id}")
	public Subscriber getSubscriberById(@PathVariable("id") String subscriberId) {
		return service.getSubscriberById(subscriberId);
	}
	
	@PostMapping("/subscribers/")
	public void createSubscriber(
			@RequestParam(value = "phoneNumber") String phoneNumber,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "egn") String egn,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "city") String city,
			@RequestParam(value = "zipCode") String zipCode,
			@RequestParam(value = "Street") String street,
			@RequestParam(value = "bankId") String bankIdString
			) {
		
		int bankId = Integer.parseInt(bankIdString);
		Client bank = new Client(bankId);
		Address address = new Address(country, city, zipCode, street);
		Subscriber newSubscriber = new Subscriber(phoneNumber, firstName, lastName, egn, address, bank);
		service.create(newSubscriber);
	}
	
	@PutMapping("/subscribers/{phoneNumber}")
	public void updateSubscriber(
			@PathVariable(value = "phoneNumber") String phoneNumber,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "egn") String egn,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "city") String city,
			@RequestParam(value = "zipCode") String zipCode,
			@RequestParam(value = "Street") String street,
			@RequestParam(value = "bankId") String bankIdString
	) {
		
		int bankId = Integer.parseInt(bankIdString);
		Client bank = new Client(bankId);
		Address address = new Address(country, city, zipCode, street);
		Subscriber updateSubscriber = new Subscriber(phoneNumber, firstName, lastName, egn, address, bank);
		service.update(phoneNumber, updateSubscriber);
	}
	
	@DeleteMapping("/subscribers/{id}")
	public void deleteSubscriber(@PathVariable("id") String subscriberId) {
		service.deleteSubscriber(subscriberId);
	}
	
	// bill requests
	@GetMapping("/bills/")
	public List<Bill> getAllBills() {
		return service.getAllBills();
	}
	
	@GetMapping("/bills")
	public List<Bill> getAllBillsByTimePeriod(
			@RequestParam(value = "startDate") String startDateString,
			@RequestParam(value = "endDate") String endDateString) throws ParseException
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(startDateString);
		Date endDate = format.parse(endDateString);
		
		return service.getAllBills(startDate, endDate);
	}
	
	@GetMapping("/bills/{id}")
	public Bill getBillById(@PathVariable("id") String billIdString) {
		int billId = Integer.parseInt(billIdString);
		return service.getBillById(billId);
	}
	
	@PostMapping("/bills/")
	public void createBill(
			@RequestParam(value = "serviceId") String serviceIdString,
			@RequestParam(value = "subscriberPhoneNumber") String subscriberPhoneNumber,
			@RequestParam(value = "startDate") String startDateString,
			@RequestParam(value = "endDate") String endDateString,
			@RequestParam(value = "amount") String amountString,
			@RequestParam(value = "currencyId") String currencyIdString,
			@RequestParam(value = "paymentDate") String paymentDateString
	) throws ParseException {
		
		Service service = new Service(Integer.parseInt(serviceIdString));
		Subscriber subscriber = new Subscriber(subscriberPhoneNumber);
		Currency currency = new Currency(Integer.parseInt(currencyIdString));
		double amount = Double.parseDouble(amountString);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(startDateString);
		Date endDate = format.parse(endDateString);
		Date paymentDate = format.parse(paymentDateString);
		
		
		Bill newBill = new Bill(service, subscriber, currency, startDate, endDate, amount, paymentDate);
		this.service.create(newBill);
	}
	
	@PutMapping("/bills/{id}")
	public void updateBill(
			@PathVariable(value = "id") String billIdString,
			@RequestParam(value = "serviceId") String serviceIdString,
			@RequestParam(value = "subscriberPhoneNumber") String subscriberPhoneNumber,
			@RequestParam(value = "startDate") String startDateString,
			@RequestParam(value = "endDate") String endDateString,
			@RequestParam(value = "amount") String amountString,
			@RequestParam(value = "currencyId") String currencyIdString,
			@RequestParam(value = "paymentDate") String paymentDateString
	) throws ParseException {
		
		int billId = Integer.parseInt(billIdString);
		Service service = new Service(Integer.parseInt(serviceIdString));
		Subscriber subscriber = new Subscriber(subscriberPhoneNumber);
		Currency currency = new Currency(Integer.parseInt(currencyIdString));
		double amount = Double.parseDouble(amountString);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = format.parse(startDateString);
		Date endDate = format.parse(endDateString);
		Date paymentDate = format.parse(paymentDateString);
		
		
		Bill updateBill = new Bill(service, subscriber, currency, startDate, endDate, amount, paymentDate);
		this.service.update(billId, updateBill);
	}
	
	@DeleteMapping("/bills/{id}")
	public void deleteBill(@PathVariable("id") String billIdString) {
		int billId = Integer.parseInt(billIdString);
		service.deleteBill(billId);
	}
}
