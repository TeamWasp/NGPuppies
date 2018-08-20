package com.telerikacademy.ngpuppies.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserSqlRepository {
	private SessionFactory factory;

	@Autowired
    public UserSqlRepository(SessionFactory factory) {
        this.factory = factory;
    }
}
