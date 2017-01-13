package com.endava.springrestexample.entity;


import com.endava.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Employee implements Serializable {

    @Id @GeneratedValue
    @JsonView(Views.EmployeePrivateView.class)
    private Integer id;

    @JsonView(Views.EmployeePublicView.class)
    private String firstName;

    @JsonView(Views.EmployeePublicView.class)
    private String lastName;

    @JsonView(Views.EmployeePrivateView.class)
    private Address address;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
