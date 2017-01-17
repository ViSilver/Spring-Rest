package com.example.springtestexample.repository;


import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.EmployeeRepository;
import com.example.springrestexample.util.config.JavaConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JavaConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findByFirstNameReturnsEmployees() {
        entityManager.persist(new Employee("John", "Smith"));
        assertNotNull(employeeRepository.findByFirstName("John"));

        Employee employee = employeeRepository.findByFirstName("John").get(0);
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void findByIdReturnsEmployee() {
        Employee employee = new Employee("John", "Smith");
        employeeRepository.save(employee);
        assertEquals(java.util.Optional.ofNullable(employee.getId()), 1);
        employee = employeeRepository.findById(employee.getId());
        assertNotNull(employee);
        assertEquals(employee.getFirstName(), "John");
        assertEquals(employee.getLastName(), "Smith");
    }
}
