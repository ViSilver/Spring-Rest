package com.example.springrestexample.service;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;

public class EmployeeService {

    private AddressService addressService;

    public EmployeeService(AddressService addressService) {
        this.addressService = addressService;
    }

    public Employee getEmployeeByAddress(final Address address) {
        Address someRandomAddress = addressService.getSomeRandomAddress();

        Address newAddress = addressService.getSomeRandomAddress();

        if (address.getCity().equals(someRandomAddress.getCity())) {
            return new Employee("Adrian", "Vrabie");
        }

        return null;
    }

}
