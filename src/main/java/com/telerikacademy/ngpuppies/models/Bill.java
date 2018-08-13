package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BillID")
    private int billId;

    @ManyToOne
    @JoinColumn(name="ServiceID")
    private Service service;

    @ManyToOne
    @JoinColumn(name="SubscriberID")
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "CurrencyID")
    private Currency currency;

    @Column(name="StartDate")
    private Date startDate;

    @Column(name="EndDate")
    private Date endDate;

    @Column(name="Amount")
    private int amount;

    public Bill(Service service, Subscriber subscriber, Currency currency, Date startDate, Date endDate, int amount) {
        this.service = service;
        this.subscriber = subscriber;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Bill() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
