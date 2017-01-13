package com.endava.springrestexample.entity;


import com.endava.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id @GeneratedValue
    @JsonView(Views.AddressPrivateView.class)
    private Integer id;

    @JsonView(Views.AddressPublicView.class)
    private String city;

    @JsonView(Views.AddressPrivateView.class)
    private String street;

    @JsonView(Views.AddressPrivateView.class)
    private String number;

    public Address() {
    }

    public Address(String city, String street, String number) {
        this.street = street;
        this.city = city;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
