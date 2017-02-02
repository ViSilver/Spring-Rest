package com.example.springtestexample.controller.rest;


import com.example.springrestexample.controller.rest.EmployeeRestController;
import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.config.JavaConfig;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
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
        address.setId(1);

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
    public void testGetAllEmployeesSuccess_EmployeePublicView() throws Exception {
        mockMvc.perform(get("/rest/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Smith")));
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testGetAllEmployeesSuccess_EmployeePrivateView() throws  Exception {
        mockMvc.perform(get("/rest/employees/private"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Sherlock")))
                .andExpect(jsonPath("$[1].lastName", is("Holmes")))
                .andExpect(jsonPath("$[1].address.id", is(1)))
                .andExpect(jsonPath("$[1].address.city", is("London")));
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testGetAllEmployeesSuccess_AllPrivateView() throws  Exception {
        mockMvc.perform(get("/rest/employees/all-private"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1]").value(hasKey("id")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Sherlock")))
                .andExpect(jsonPath("$[1].lastName", is("Holmes")))
                .andExpect(jsonPath("$[1].address.id", is(1)))
                .andExpect(jsonPath("$[1].address.city", is("London")))
                .andExpect(jsonPath("$[1].address.street", is("Baker Street")))
                .andExpect(jsonPath("$[1].address.number", is("221 B")))
                .andExpect(jsonPath("$[0].address").value(nullValue(String.class)));
        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }
}
