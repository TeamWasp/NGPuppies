package com.telerikacademy.ngpuppies;

import com.telerikacademy.ngpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class NgPuppiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NgPuppiesApplication.class, args);
    }
}
