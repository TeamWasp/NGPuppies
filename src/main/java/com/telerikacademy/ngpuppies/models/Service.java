package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID")
    private int serviceId;
    
    @NotNull
    @Size(min = 4, max = 10)
    @Column(name = "Name", unique = true)
    private String name;
    
    public Service() {
    }
    
    public Service(int serviceId, String name) {
        this.serviceId = serviceId;
        this.name = name;
    }
    
    public Service(int serviceId) {
        this.serviceId = serviceId;
    }
    
    public Service(String name) {
        this.name = name;
    }
    
    public int getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Service{" +
            "serviceId='" + serviceId + '\'' +
            ", name=" + name +
            '}';
    }
}
