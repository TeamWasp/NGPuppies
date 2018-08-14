package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;

@Entity
@Table(name="subscribers")
public class Subscriber {
    @Id
    @Column(name="PhoneNumber")
    private String phoneNumber;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @Column(name="EGN")
    private String EGN;

    @Column(name = "Address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "BankID")
    private User bank;

    public Subscriber(String phoneNumber, String firstName, String lastName, String EGN, String address, User bank) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.EGN = EGN;
        this.address = address;
        this.bank = bank;
    }

    public Subscriber() {
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

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getBank() {
        return bank;
    }

    public void setBank(User bank) {
        this.bank = bank;
    }
}
