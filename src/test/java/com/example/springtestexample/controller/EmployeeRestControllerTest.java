package com.example.springtestexample.controller;


import com.example.springrestexample.controller.EmployeeRestController;
import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.main.Application;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.config.JavaConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {JavaConfig.class})
//@ContextConfiguration
@WebAppConfiguration
public class EmployeeRestControllerTest {

    private MediaType contentType;

    private MockMvc mockMvc;

    private Integer userId;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Employee employee;

    private List<Address> addresses = new ArrayList<>();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        employeeRepository.deleteAll();
        addressRepository.deleteAll();

        employee = employeeRepository.save(new Employee("John", "Smith"));
        addresses.add(addressRepository.save(new Address("London", "Baker Street", "221 B")));
        addresses.add(addressRepository.save(new Address("London", "Baker Street", "221 A")));

        userId = employee.getId();
    }

    @Test
    public void readSingleEmployee() throws Exception {
//        assertEquals(Optional.ofNullable(userId), 1);
        mockMvc.perform(get("/employee/" + 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())));
    }

    @Test
    public void dummyGet() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/employee/{id}", id).accept(MediaType.APPLICATION_JSON_UTF8));
    }

    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {

        @Bean
        public EmployeeRestController employeeRestController() {
            return new EmployeeRestController();
        }
    }
}
