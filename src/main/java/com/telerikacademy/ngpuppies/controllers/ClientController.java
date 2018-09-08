package com.telerikacademy.ngpuppies.controllers;

import com.mchange.rmi.NotAuthorizedException;
import com.telerikacademy.ngpuppies.models.Bill;
import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.models.dto.BillDTO;
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
    public ClientController(ClientService clientService, UserService userService, TokenService<HttpServletRequest> tokenService) {
        this.clientService = clientService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/subs")
    public List<Subscriber> getAllSubscribers(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        return clientService.getAllSubscribers(userId);
    }

    @GetMapping("/subs/{id}")
    public Subscriber getSubscriber(@PathVariable("id") String id, HttpServletRequest request) {
        try {
            return clientService.getSubscriber(id, request);
        }
        catch (NotAuthorizedException ex){
            ex.getMessage();
        }
        return null;
    }

    @GetMapping("/top")
    public List<SubscriberDTO> getTopTenSubscribers(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));

        return clientService.getTopTenSubscribers(userId);
    }

    @PutMapping("/bills/pay")
    public void payBills(@RequestBody List<String> billsId, HttpServletRequest req) {
        try {
            clientService.payMultipleBills(billsId, req);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GetMapping("/bills")
    public List<Bill> getAllBills(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        return clientService.getAllBills(userId);
    }

    @GetMapping("/bills/unpaid")
    public List<Bill> getUnpaidBills(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        return clientService.getUnpaidBills(userId);
    }

    @GetMapping("/bills/history")
    public List<Bill> getPaymentHistory(HttpServletRequest request) {
        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        return clientService.getPaymentHistory(userId);
    }

    @GetMapping("bills/{subscriberId}/{timeInterval}")
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval,
            @PathVariable("subscriberId") String subscriberId,
            HttpServletRequest request) {

        int userId = userService.getIdByUsername(tokenService.getUsernameFromToken(request));
        return clientService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval, subscriberId, userId);
    }

}
