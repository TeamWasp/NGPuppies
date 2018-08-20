package com.telerikacademy.ngpuppies.services.base;

import com.telerikacademy.ngpuppies.models.Subscriber;

import java.util.List;

public interface ClientService {
    List<Subscriber> getAllSubscribers(int userId);
    List<Subscriber> getTopTenSubscribers(int userId);
}
