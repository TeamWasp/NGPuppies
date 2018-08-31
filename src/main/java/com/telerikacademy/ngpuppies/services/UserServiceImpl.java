package com.telerikacademy.ngpuppies.services;


import com.telerikacademy.ngpuppies.models.User;
import com.telerikacademy.ngpuppies.repositories.UserSqlRepository;
import com.telerikacademy.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserSqlRepository userRepository;

    public UserServiceImpl(UserSqlRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public int getIdByUsername(String username) {
        return userRepository.getIdByUsername(username);
    }
}