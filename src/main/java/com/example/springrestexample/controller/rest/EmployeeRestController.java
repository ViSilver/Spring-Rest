package com.example.springrestexample.controller.rest;

import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRestController(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @JsonView(Views.EmployeePublicView.class)
    @RequestMapping(value = "/{id}", produces = "application/json")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    @JsonView(Views.EmployeePrivateView.class)
    @RequestMapping(value = "/private/{id}", produces = "application/json")
    public Employee getPrivateEmployee(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    @JsonView(Views.AllPrivateView.class)
    @RequestMapping(value = "/all-private/{id}", produces = "application/json")
    public Employee getAllPrivateFieldsOfEmployee(@PathVariable Integer id) {
        return employeeRepository.findById(id);
    }

    @JsonView(Views.EmployeePublicView.class)
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Employee input) {
        Employee result = employeeRepository.save(new Employee(input.getFirstName(), input.getLastName()));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @JsonView(Views.EmployeePrivateView.class)
    @RequestMapping(value = "private", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Employee> getAllPrivateEmployees() {
        return employeeRepository.findAll();
    }

    @JsonView(Views.AllPrivateView.class)
    @RequestMapping(value = "all-private", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Employee> getAllPrivate() {
        return employeeRepository.findAll();
    }
}
