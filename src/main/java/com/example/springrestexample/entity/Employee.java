package com.example.springrestexample.entity;

import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
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

    public boolean hasAddress() {
        return this.address != null;
    }
}
