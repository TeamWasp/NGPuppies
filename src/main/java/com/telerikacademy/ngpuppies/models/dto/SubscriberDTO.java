package com.telerikacademy.ngpuppies.models.dto;

public class SubscriberDTO {
    private String phoneNumber;
    private String firstName;
    private String lastName;
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
}
