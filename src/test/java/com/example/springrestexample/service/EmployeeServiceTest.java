package com.example.springrestexample.service;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp() {
//        addressService = new AddressService();
//        employeeService = new EmployeeService(addressService);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(addressService);
    }

    @Test
    public void testGetEmployeeByAddress() {
        final Employee expected = new Employee("Adrian", "Vrabie");
        final Address address = new Address("Chisinau", "Dacia", "49");
        when(addressService.getSomeRandomAddress()).thenReturn(address);

        final Employee actual = employeeService.getEmployeeByAddress(address);

        assertEquals(expected, actual);
        verify(addressService, times(2)).getSomeRandomAddress();
    }


}