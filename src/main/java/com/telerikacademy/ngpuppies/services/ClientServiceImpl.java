package com.telerikacademy.ngpuppies.services;

import com.telerikacademy.ngpuppies.models.Subscriber;
import com.telerikacademy.ngpuppies.repositories.base.ClientRepository;
import com.telerikacademy.ngpuppies.services.base.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Subscriber> getAllSubscribers(int userId) {
        return repository.getAllSubscribers(userId);
    }

    @Override
    public List<Subscriber> getTopTenSubscribers(int userId) {
        return repository.getTopTenSubscribers(userId);
    }
}
