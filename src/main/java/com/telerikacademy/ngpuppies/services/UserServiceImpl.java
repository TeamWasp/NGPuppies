package com.telerikacademy.ngpuppies.services;


import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.repositories.UserSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserSqlRepository userRepository;


    public UserServiceImpl(UserSqlRepository userRepository) {
        this.userRepository = userRepository;

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }
}