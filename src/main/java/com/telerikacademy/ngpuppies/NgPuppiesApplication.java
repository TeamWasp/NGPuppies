package com.telerikacademy.ngpuppies;

import com.telerikacademy.ngpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NgPuppiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NgPuppiesApplication.class, args);
    }

    @Bean
    public SessionFactory createSessionFactory() {
        System.out.println("SessionFactory was created.");
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Bill.class)
                .addAnnotatedClass(Currency.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Subscriber.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

    }
}
