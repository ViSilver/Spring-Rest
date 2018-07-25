package com.example.springrestexample.service;

import com.example.springrestexample.entity.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressServiceTest {

    private AddressService addressService;

    @Before
    public void setUp() {
        addressService = new AddressService();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetSomeRandomAddress() {

        Address expected = new Address("Chisinau", "Dacia", "49");

        Address actual = addressService.getSomeRandomAddress();

        assertEquals(expected, actual);

    }

    @Test
    public void getSomeOtherAddress() {

    }
}