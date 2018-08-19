package com.telerikacademy.ngpuppies.config;

import com.telerikacademy.ngpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	private static SessionFactory factory;
	
	static {
		factory = new org.hibernate.cfg.Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Client.class)
				.addAnnotatedClass(Client.class)
				.addAnnotatedClass(Admin.class)
				.addAnnotatedClass(Role.class)
				.addAnnotatedClass(Subscriber.class)
				.addAnnotatedClass(Bill.class)
				.addAnnotatedClass(Address.class)
				.addAnnotatedClass(Service.class)
				.addAnnotatedClass(Currency.class)
				.buildSessionFactory();
	}
	
	@Bean
	public SessionFactory sessionFactory() {
		System.out.println("SessionFactory was created.");
		return factory;
	}
}
