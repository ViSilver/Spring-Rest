package com.endava.springrestexample.repository;


import com.endava.springrestexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findById(Integer id);

    List<Employee> findByFirstName(String firstName);

    @Transactional
    void deleteByFirstName(String firstName);
}
