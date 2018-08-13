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

    public Currency(String currency) {
        this.currency = currency;
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
}
