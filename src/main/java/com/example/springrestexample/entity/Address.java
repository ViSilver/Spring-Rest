package com.example.springrestexample.entity;

import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@Entity
public class Address implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.AddressPublicView.class)
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
}
