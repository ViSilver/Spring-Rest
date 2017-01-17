package com.example.springrestexample.controller;


import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.serialization.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

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
    @RequestMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Employee input) {
        Employee result = employeeRepository.save(new Employee(input.getFirstName(), input.getLastName()));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
