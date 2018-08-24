package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public List<Subscriber> getAllSubscribers(@PathVariable("id") String idString) {
        return service.getAllSubscribers(Integer.parseInt(idString));
    }

    @GetMapping("/top/{id}")
    public List<Subscriber> getTopTenSubscribers(@PathVariable("id") String idString) {
        return service.getTopTenSubscribers(Integer.parseInt(idString));
    }

    @PutMapping("/pay/{id}")
    public void payBill(@PathVariable("id") String idString) {
        service.payBill(Integer.parseInt(idString));
    }

    @GetMapping("/bills/{id}")
    public List<Bill> getAllBills(@PathVariable("id") String idString) {
        return service.getAllBills(Integer.parseInt(idString));
    }

    @GetMapping("/bills/{id}/unpaid")
    public List<Bill> getUnpaidBills(@PathVariable("id") String idString) {
        return service.getUnpaidBills(Integer.parseInt(idString));
    }
}
