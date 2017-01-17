package com.example.springrestexample.controller;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressRestController {

    @Autowired
    private AddressRepository addressRepository;

    @JsonView(Views.AddressPublicView.class)
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Address get(@PathVariable Integer id) {
        return addressRepository.findById(id);
    }

    @JsonView(Views.AddressPrivateView.class)
    @RequestMapping(value = "/private/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Address getPrivate(@PathVariable Integer id) {
        return addressRepository.findById(id);
    }
}
