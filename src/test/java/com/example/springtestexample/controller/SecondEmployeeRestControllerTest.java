package com.example.springtestexample.controller;


import com.example.springrestexample.controller.EmployeeRestController;
import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.config.JavaConfig;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaConfig.class}, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecondEmployeeRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeRestController employeeRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeRestController)
//                .addFilter(new CorsFilter())
                .build();

        Address address = new Address("London", "Baker Street", "221 B");

        List<Employee> employees = Arrays.asList(
                new Employee("John", "Smith"),
                new Employee("Sherlock", "Holmes"));
        employees.get(0).setId(1);
        employees.get(1).setId(2);
        employees.get(1).setAddress(address);

        when(employeeRepository.findAll()).thenReturn(employees);
        given(this.employeeRepository.findById(1)).willReturn(employees.get(0));
        given(this.employeeRepository.findById(2)).willReturn(employees.get(1));
    }

    @Test
    public void testGetAllSuccess() throws Exception{
        mockMvc.perform(get("/employee/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Smith")));
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }
}
