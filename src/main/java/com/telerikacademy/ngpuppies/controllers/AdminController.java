package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.*;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		try {
			int adminId = Integer.parseInt(adminIdString);
			return service.getAdminById(adminId);
		} catch (Exception ex) {
			System.out.println("Incorrect admin id");
			return null;
		}
	}
	
	@PostMapping("/admins/createAdmin")
	public void createAdmin(@RequestBody Admin newAdmin) {
		service.create(newAdmin);
	}
	
	@PutMapping("/admins/updateAdmin/{id}")
	public void updateAdmin(
			@PathVariable("id") String adminIdString,
			@RequestBody Admin updatedAdmin)
	{
		try {
			int adminId = Integer.parseInt(adminIdString);
			service.update(adminId, updatedAdmin);
		} catch (Exception ex) {
			System.out.printf("Admin Id \"%s\" is incorrectly typed!", adminIdString);
		}
	}
	
	@DeleteMapping("/admins/deleteAdmin/{id}")
	public void deleteAdmin(@PathVariable("id") String adminIdString) {
		
		try {
			int adminId = Integer.parseInt(adminIdString);
			service.deleteAdmin(adminId);
		} catch (Exception ex) {
			System.out.println("Incorrect admin id");
		}
	}
	
	// users requests
	@GetMapping("/users/")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") String userIdString) {
		
		try {
			int userId = Integer.parseInt(userIdString);
			return service.getUserById(userId);
		} catch (Exception ex) {
			System.out.println("Incorrect user id");
			return null;
		}
	}
	
	@GetMapping("/users")
	public int getIdByUsername(@RequestParam(value = "username", required = false) String username) {
		return service.getIdByUsername(username);
	}
	
	@DeleteMapping("/users/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") String userIdString) {
		try {
			int userId = Integer.parseInt(userIdString);
			service.deleteUser(userId);
		} catch (Exception ex) {
			System.out.println("Incorrect user id");
		}
	}
	
	// clients requests
	@GetMapping("/clients/")
	public List<Client> getAllClients() {
		return service.getAllClients();
	}
	
	@GetMapping("/clients/{id}")
	public Client getClientById(@PathVariable("id") String clientIdString) {
		try {
			int clientId = Integer.parseInt(clientIdString);
			return service.getClientById(clientId);
		} catch (Exception ex) {
			System.out.println("Incorrect client id");
			return null;
		}
	}
	
	@PostMapping("/clients/createClient")
	public void createClient(@RequestBody Client newClient) {
		service.create(newClient);
	}
	
	@PutMapping("/clients/updateClient/{id}")
	public void updateClient(
			@PathVariable("id") String clientIdString,
			@RequestBody Client updateClient)
	{
		try {
			int clientId = Integer.parseInt(clientIdString);
			service.update(clientId, updateClient);
		} catch (Exception ex) {
			System.out.printf("Client Id \"%s\" is incorrectly typed!", clientIdString);
		}
	}
	
	@DeleteMapping("/clients/deleteClient/{id}")
	public void deleteClient(@PathVariable("id") String clientIdString) {
		try {
			int clientId = Integer.parseInt(clientIdString);
			service.deleteClient(clientId);
		} catch (Exception ex) {
			System.out.println("Incorrect client id");
		}
	}
	
	// service requests
	@GetMapping("/services/")
	public List<Service> getAllServices() {
		return service.getAllServices();
	}
	
	@GetMapping("/services/{id}")
	public Service getServiceById(@PathVariable("id") String serviceIdString) {
		try {
			int serviceId = Integer.parseInt(serviceIdString);
			return service.getServiceById(serviceId);
		} catch (Exception ex) {
			System.out.println("Incorrect service id");
			return null;
		}
	}
	
	@PostMapping("/services/createService")
	public void createService(@RequestBody Service newService) {
		service.create(newService);
	}
	
	@PutMapping("/services/updateService/{id}")
	public void updateService(
			@PathVariable("id") String serviceIdString,
			@RequestBody Service updateService)
	{
		try {
			int serviceId = Integer.parseInt(serviceIdString);
			service.update(serviceId, updateService);
		} catch (Exception ex) {
			System.out.printf("Service Id \"%s\" is incorrectly typed!", serviceIdString);
		}
	}
	
	@DeleteMapping("/services/deleteService/{id}")
	public void deleteService(@PathVariable("id") String serviceIdString) {
		try {
			int serviceId = Integer.parseInt(serviceIdString);
			service.deleteService(serviceId);
		} catch (Exception ex) {
			System.out.println("Incorrect service id");
		}
	}
	
	// currency requests
	@GetMapping("/currencies/")
	public List<Currency> getAllCurrencies() {
		return service.getAllCurrencies();
	}
	
	@GetMapping("/currencies/{id}")
	public Currency getCurrencyById(@PathVariable("id") String currencyIdString) {
		try {
			int currencyId = Integer.parseInt(currencyIdString);
			return service.getCurrencyById(currencyId);
		} catch (Exception ex) {
			System.out.println("Incorrect currency id");
			return null;
		}
	}
	
	@PostMapping("/currencies/createCurrency")
	public void createCurrency(@RequestBody Currency newCurrency) {
		service.create(newCurrency);
	}
	
	@PutMapping("/currencies/updateCurrency/{id}")
	public void updateCurrency(
			@PathVariable("id") String currencyIdString,
			@RequestBody Currency updateCurrency)
	{
		try {
			int currencyId = Integer.parseInt(currencyIdString);
			service.update(currencyId, updateCurrency);
		} catch (Exception ex) {
			System.out.printf("Currency Id \"%s\" is incorrectly typed!", currencyIdString);
		}
	}
	
	@DeleteMapping("/currencies/deleteCurrency/{id}")
	public void deleteCurrency(@PathVariable("id") String currencyIdString) {
		try {
			int currencyId = Integer.parseInt(currencyIdString);
			service.deleteCurrency(currencyId);
		} catch (Exception ex) {
			System.out.println("Incorrect currency id");
		}
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
		try {
			int bankId = Integer.parseInt(bankIdString);
			return service.getAllSubscribers(bankId);
		} catch (Exception ex) {
			System.out.println("Incorrect bank id");
			return null;
		}
	}
	
	@GetMapping("/subscribers/{id}")
	public Subscriber getSubscriberById(@PathVariable("id") String subscriberId) {
		return service.getSubscriberById(subscriberId);
	}
	
	@GetMapping("/subscribersDTO/{id}")
	public SubscriberDTO loadSubscriberById(@PathVariable("id") String subscriberId) {
		return service.loadSubscriberById(subscriberId);
	}
	
	@PostMapping("/subscribers/createSubscriber")
	public void createSubscriber(@RequestBody SubscriberDTO newSubscriber) {
		service.create(newSubscriber);
	}
	
	@PutMapping(value = "/subscribers/updateSubscriber/{phoneNumber}")
	public void updateSubscriber(
			@PathVariable("phoneNumber") String phoneNumber,
			@RequestBody SubscriberDTO updateSubscriberDto)
	{
		service.update(phoneNumber, updateSubscriberDto);
	}
	
	@DeleteMapping("/subscribers/deleteSubscriber/{id}")
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
			@RequestParam(value = "endDate") String endDateString)
	{
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse(startDateString);
			Date endDate = format.parse(endDateString);
			return service.getAllBills(startDate, endDate);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	@GetMapping("/bills/{id}")
	public Bill getBillById(@PathVariable("id") String billIdString) {
		try {
			int billId = Integer.parseInt(billIdString);
			return service.getBillById(billId);
		} catch (Exception ex) {
			System.out.println("Incorrect bill id");
			return null;
		}
	}
	
	@PostMapping("/bills/createBill")
	public void createBill(@RequestBody Bill newBill) {
		this.service.create(newBill);
	}
	
	@PutMapping("/bills/updateBill/{id}")
	public void updateBill(
			@PathVariable(value = "id") String billIdString,
			@RequestBody Bill updateBill)
	{
		try {
			int billId = Integer.parseInt(billIdString);
			service.update(billId, updateBill);
		} catch (Exception ex) {
			System.out.printf("Bill Id \"%s\" is incorrectly typed!", billIdString);
		}
	}
	
	@DeleteMapping("/bills/deleteBill/{id}")
	public void deleteBill(@PathVariable("id") String billIdString) {
		try {
			int billId = Integer.parseInt(billIdString);
			service.deleteBill(billId);
		} catch (Exception ex) {
			System.out.println("Incorrect bill id");
		}
	}
}
