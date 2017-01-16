package com.endava.springrestexample.controller;


import com.endava.springrestexample.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @RequestMapping("/{id}")
    public Employee getEmployee() {
        return new Employee("John", "Smith");
    }
}
