package com.example.springrestexample.entity;


import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Employee implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.EmployeePublicView.class)
    private Integer id;

    @JsonView(Views.EmployeePublicView.class)
    private String firstName;

    @JsonView(Views.EmployeePublicView.class)
    private String lastName;

    @OneToOne
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

    @Override
    public String toString() {
        Integer addressId = null;

        if (address != null) {
            addressId = address.getId();
        }

        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + addressId +
                '}';
    }
}
