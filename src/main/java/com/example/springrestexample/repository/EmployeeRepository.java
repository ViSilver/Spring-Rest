package com.example.springrestexample.repository;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(Integer id);

    List<Employee> findByFirstName(String firstName);

    Employee findByAddress(Address address);

    @Transactional
    void deleteByFirstName(String firstName);
}
