package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public List<Subscriber> getAllSubscribers(@PathVariable("id") String idString){
        return service.getAllSubscribers(Integer.parseInt(idString));
    }

    @GetMapping("/top/{id}")
    public List<Subscriber> getTopTenSubscribers(@PathVariable("id") String idString){
        return service.getTopTenSubscribers(Integer.parseInt(idString));
    }
    @GetMapping("/pay/{id}")
    public void payBill(@PathVariable("id") String idString){
        service.payBill(Integer.parseInt(idString));
    }
}
