package com.example.springrestexample.service;

import com.example.springrestexample.entity.Address;

public class AddressService {

    public Address getSomeRandomAddress() {
        return new Address("Chisinau", "Dacia", "49");
    }
}
