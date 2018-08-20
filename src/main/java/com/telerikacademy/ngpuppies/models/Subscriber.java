package com.telerikacademy.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subscribers")
public class Subscriber {
    
    @Id
    @Size(min=9, max = 15)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @NotNull
    @Size(min=3, max = 10)
    @Column(name = "FirstName")
    private String firstName;
    
    @NotNull
    @Size(min=4, max = 15)
    @Column(name = "LastName")
    private String lastName;
    
    @NotNull
    @Size(min = 10, max = 10)
    @Column(name = "EGN", unique = true)
    private String egn;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "AddressID")
    @JsonManagedReference
    private Address address;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "BankID")
    @JsonManagedReference
    private Client bank;
    
    public Subscriber() {
    }
    
    public Subscriber(String phoneNumber, String firstName, String lastName, String egn, Address address, Client bank) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.address = address;
        this.bank = bank;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEgn() {
        return egn;
    }
    
    public void setEgn(String egn) {
        this.egn = egn;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Client getBank() {
        return bank;
    }
    
    public void setBank(Client bank) {
        this.bank = bank;
    }
}
