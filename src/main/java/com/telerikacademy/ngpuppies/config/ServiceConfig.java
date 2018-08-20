package com.telerikacademy.ngpuppies.config;

import com.telerikacademy.ngpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	@Bean
	public SessionFactory createSessionFactory() {
		System.out.println("SessionFactory was created.");
		
		return new org.hibernate.cfg.Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Client.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Admin.class)
				.addAnnotatedClass(Role.class)
				.addAnnotatedClass(Subscriber.class)
				.addAnnotatedClass(Bill.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Service.class)
				.addAnnotatedClass(Currency.class)
				.buildSessionFactory();
	}
}
