package com.example.springtestexample.controller.rest;


import com.example.springrestexample.controller.AddressController;
import com.example.springrestexample.controller.rest.AddressRestController;
import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.util.config.JavaConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {JavaConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressRestController addressRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressRestController)
                .build();

        List<Address> addresses = Arrays.asList(
                new Address("London", "Baker Street", "221 B"),
                new Address("Chisinau", "Stefan cel Mare", "1")
        );
        addresses.get(0).setId(1);
        addresses.get(1).setId(2);

        when(addressRepository.findAll()).thenReturn(addresses);
        given(this.addressRepository.findById(1)).willReturn(addresses.get(0));
        given(this.addressRepository.findById(2)).willReturn(addresses.get(1));
    }

    @Test
    public void getAllAddressesSuccess_AddressPublicView() throws Exception {
        mockMvc.perform(get("/rest/addresses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].city", is("London")));
        verify(addressRepository, times(1)).findAll();
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    public void getAddressByIdSuccess_AddressPublicView() throws Exception {
        mockMvc.perform(get("/rest/addresses/1"))
                .andExpect(status().isOk());
    }
}
