package com.telerikacademy.ngpuppies.controllers;

import com.telerikacademy.ngpuppies.security.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailSenderController {

    @Autowired
    private SmtpMailSender smtpMailSender;

    @GetMapping("/api/sendmail")
    public void sendMail() throws MessagingException {
        smtpMailSender.send("megata10@gmail.com","Test","Test");
    }
}
