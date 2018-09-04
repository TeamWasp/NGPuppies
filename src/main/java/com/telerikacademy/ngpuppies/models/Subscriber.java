package com.telerikacademy.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.telerikacademy.ngpuppies.serializers.SubscriberSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "subscribers")
//@JsonSerialize(using = SubscriberSerializer.class)
public class Subscriber implements Comparable<Subscriber> {
    
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
    @OneToOne
    @JoinColumn(name = "AddressID")
    //@JsonManagedReference(value = "address")
    private Address address;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "BankID")
    @JsonManagedReference(value = "bank")
    private Client bank;

    @OneToMany(mappedBy = "subscriber",fetch = FetchType.EAGER)
    @JsonBackReference(value = "bills") // Prevents from infinite loops in Json (one class calls a second and the second calls the first infinitely)
    private List<Bill> bills;

    public Subscriber() {
    }
    
    public Subscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    
    @Override
    public String toString() {
        return "Subscriber{" +
            "phoneNumber='" + phoneNumber + '\'' +
            ", firstName=" + firstName + '\'' +
            ", lastName=" + lastName + '\'' +
            ", EGN=" + egn + '\'' +
            ", addressId=" + address.getAddressId() +
            ", bankId=" + bank.getUserId() +
            '}';
    }
    
    @Override
    public int compareTo(Subscriber o) {
        int compare=0;
        int compared=0;
        for (Bill bill:this.bills) {
            compare+=bill.getAmount();

        }
        for (Bill bill:o.getBills()) {
            compared+=bill.getAmount();

        }
        return compared-compare;
    }
}
