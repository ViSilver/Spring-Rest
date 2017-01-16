package com.example.springtestexample.entity.serialization;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeJsonSerializationTest {

    private Address address = mock(Address.class);

    @Before
    public void initAddressMock() {
        when(address.getCity()).thenReturn("London");
        when(address.getStreet()).thenReturn("Baker Street");
        when(address.getNumber()).thenReturn("221 B");
    }

    @Test
    public void whenUseEmployeePublicViewJsonViewToSerialize_thenCorrect() throws JsonProcessingException {

        Employee employee = new Employee("John", "Smith");
        employee.setAddress(address);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String result = mapper
                .writerWithView(Views.EmployeePublicView.class)
                .writeValueAsString(employee);

        assertThat(result, containsString("John"));
        assertThat(result, containsString("Smith"));
        assertThat(result, not(containsString("Baker Street")));
        assertThat(result, not(containsString("London")));
    }

    @Test
    public void whenUseEmployeePrivateViewJsonViewToSerialize_thenCorrect() throws JsonProcessingException {

        Employee employee = new Employee("John", "Smith");
        employee.setAddress(address);


        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String result = mapper
                .writerWithView(Views.EmployeePrivateView.class)
                .writeValueAsString(employee);

        assertThat(result, containsString("id"));
        assertThat(result, containsString("John"));
        assertThat(result, containsString("Smith"));
        assertThat(result, containsString("address"));
        assertThat(result, containsString("London"));
        assertThat(result, not(containsString("Baker Street")));
        assertThat(result, not(containsString("221 B")));
    }

    @Test
    public void whenUseAllPrivateViewJsonViewToSerialize_thenCorrect() throws JsonProcessingException {

        Employee employee = new Employee("John", "Smith");
        employee.setAddress(address);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        String result = mapper
                .writerWithView(Views.AllPrivateView.class)
                .writeValueAsString(employee);

        assertThat(result, containsString("id"));
        assertThat(result, containsString("John"));
        assertThat(result, containsString("Smith"));
        assertThat(result, containsString("Baker Street"));
        assertThat(result, containsString("London"));
    }

    @Test
    public void whenUsePublicJsonViewToDeserialize_thenCorrect() throws IOException {
        String json = "{\"firstName\": \"John\",\"lastName\": \"Smith\",\"address\":{\"city\": \"London\"}}";

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper
                .readerWithView(Views.EmployeePublicView.class).withType(Employee.class).readValue(json);

        assertNull(employee.getAddress());
    }

    @Test
    public void whenUsePrivateJsonViewToDeserialize_thenCorrect() throws IOException {
        String json = "{\"id\": \"3\",\"firstName\": \"John\"," +
                "\"lastName\": \"Smith\",\"address\":{\"city\": \"London\"}}";

        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper
                .readerWithView(Views.EmployeePrivateView.class).withType(Employee.class).readValue(json);

        assertEquals(employee.getId().toString(), "3");
        assertEquals(employee.getFirstName(), "John");
        assertEquals(employee.getLastName(), "Smith");
        assertNotNull(employee.getAddress());
        assertEquals("London", employee.getAddress().getCity());
        assertNotEquals("Baker Street", employee.getAddress().getStreet());
        assertNotEquals("221 B", employee.getAddress().getNumber());
    }
}
