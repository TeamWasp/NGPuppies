package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.models.dto.SubscriberDTO;
import com.telerikacademy.ngpuppies.security.services.base.TokenService;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import com.telerikacademy.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("api/client")
public class ClientController {
    
    private ClientService clientService;
    private UserService userService;
    private TokenService<HttpServletRequest> tokenService;

    @Autowired
    public ClientController(ClientService clientService, UserService userService,TokenService<HttpServletRequest> tokenService) {
        this.clientService = clientService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/subs")
    public List<Subscriber> getAllSubscribers(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        
        return clientService.getAllSubscribers(userId);
    }

    @GetMapping("/top")
    public List<SubscriberDTO> getTopTenSubscribers(HttpServletRequest request) {
        String username = tokenService.getUsernameFromToken(request);
        User user = userService.getByUsername(username);
        int userId = user.getUserId();
        
        return clientService.getTopTenSubscribers(userId);
    }

    @PutMapping("/pay/{id}")
    public void payBill(@PathVariable("id") String idString, HttpServletRequest req) {
        try {
            int clientId = Integer.parseInt(idString);
            clientService.payBill(clientId,req);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GetMapping("/bills")
    public List<Bill> getAllBills(HttpServletRequest request) {
        String username = tokenService.getUsernameFromToken(request);
        User user = userService.getByUsername(username);
        int userId = user.getUserId();
        
        return clientService.getAllBills(userId);
    }

    @GetMapping("/bills/unpaid")
    public List<Bill> getUnpaidBills(HttpServletRequest request) {
        String username = tokenService.getUsernameFromToken(request);
        User user = userService.getByUsername(username);
        int userId = user.getUserId();
        
        return clientService.getUnpaidBills(userId);
    }
}
