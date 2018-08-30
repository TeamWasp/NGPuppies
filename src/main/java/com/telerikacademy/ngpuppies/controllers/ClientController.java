package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.SubscriberSqlRepository;
import com.telerikacademy.ngpuppies.repositories.UserSqlRepository;
import com.telerikacademy.ngpuppies.repositories.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.telerikacademy.ngpuppies.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.telerikacademy.ngpuppies.security.Constants.HEADER_STRING;
import static com.telerikacademy.ngpuppies.security.Constants.TOKEN_PREFIX;


@RestController
@RequestMapping("api/client")
public class ClientController {
    private ClientService service;
    private SubscriberSqlRepository subscriberSqlRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/subs")
    public List<Subscriber> getAllSubscribers(HttpServletRequest request) {
        return service.getAllSubscribers(service.getIdFromToken(request));
    }

    @GetMapping("/top")
    public List<SubscriberDTO> getTopTenSubscribers(HttpServletRequest request) {
        return service.getTopTenSubscribers(service.getIdFromToken(request));
    }

    @PutMapping("/pay/{id}")
    public void payBill(@PathVariable("id") String idString, HttpServletRequest req) {
        service.payBill(Integer.parseInt(idString),req);
    }

    @GetMapping("/bills")
    public List<Bill> getAllBills(HttpServletRequest request) {
        return service.getAllBills(service.getIdFromToken(request));
    }

    @GetMapping("/bills/unpaid")
    public List<Bill> getUnpaidBills(HttpServletRequest request) {
        return service.getUnpaidBills(service.getIdFromToken(request));
    }


}
