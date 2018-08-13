package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;

@Entity
@Table(name="subscribers")
public class Subscriber {
    @Id
    @Column(name="PhoneNumber")
    private int phoneNumber;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @Column(name="EGN")
    private String EGN;

    @Column(name = "Address")
    private String address;

    public Subscriber(int phoneNumber, String firstName, String lastName, String EGN, String address) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.EGN = EGN;
        this.address = address;
    }

    public Subscriber() {
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
}
