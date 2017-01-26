package com.example.springrestexample.dto;


import com.example.springrestexample.entity.Employee;

public class EmployeeAddressDto {

    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    private String firstName;

    private String lastName;

    private String city;

    private String street;

    private String number;

    public EmployeeAddressDto() {
    }

    public EmployeeAddressDto(Employee employee) {
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setEmployeeId(employee.getId());
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public EmployeeAddressDto(String firstName, String lastName, String city, String street, String number) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
