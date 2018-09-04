package com.telerikacademy.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "bills")
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillID")
    private int billId;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private Service service;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SubscriberID")
    @JsonManagedReference(value = "subscriber")
    private Subscriber subscriber;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "CurrencyID")
    private Currency currency;
    
    @NotNull
    //@DateTimeFormat
    @Temporal(TemporalType.DATE)
    @Column(name = "StartDate")
    private Date startDate;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "EndDate")
    private Date endDate;
    
    @NotNull
    @Column(name = "Amount")
    private double amount;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "PaymentDate")
    private Date paymentDate;
    
    public Bill() {}
    
    public Bill(Service service, Subscriber subscriber, Currency currency, Date startDate, Date endDate, double amount, Date paymentDate) {
        this.service = service;
        this.subscriber = subscriber;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.paymentDate = paymentDate;
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
    
    public double getAmount() {

        return amount;

    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    @Override
    public String toString() {
        return "Bill{" +
            "billId='" + billId + '\'' +
            ", serviceId=" + service.getServiceId() + '\'' +
            ", subscriberId=" + subscriber.getPhoneNumber() + '\'' +
            ", startDate=" + startDate + '\'' +
            ", endDate=" + endDate + '\'' +
            ", amount=" + amount + '\'' +
            ", currencyId=" + currency.getCurrencyId() + '\'' +
            ", paymentDate=" + paymentDate +
            '}';
    }
}
