package com.telerikacademy.ngpuppies.models.dto;

public class SubscriberDTO {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String egn;
    private String country;
    private String city;
    private String zipCode;
    private String street;
    private String bank;
    private double sumAmount;

    public SubscriberDTO() {

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
    
    public double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(double sumAmount) {
        this.sumAmount = sumAmount;
    }
    
    public String getEgn() {
        return egn;
    }
    
    public void setEgn(String egn) {
        this.egn = egn;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getBank() {
        return bank;
    }
    
    public void setBank(String bank) {
        this.bank = bank;
    }
}
