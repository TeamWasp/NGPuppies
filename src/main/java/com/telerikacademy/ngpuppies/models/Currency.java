package com.telerikacademy.ngpuppies.models;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CurrencyID")
    private int currencyId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "ExhangeRate")
    private double exchangeRate;

    public Currency(String currency, double exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public Currency() {
    }


    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int id) {
        this.currencyId = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
