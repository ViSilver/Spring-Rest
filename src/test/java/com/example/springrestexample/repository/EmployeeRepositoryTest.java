package com.example.springrestexample.repository;

import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.util.config.JavaConfig;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextBeforeModesTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JavaConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DbUnitConfiguration(databaseConnection = {"configureDataSource"})
@DatabaseSetup(EmployeeRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {EmployeeRepositoryTest.DATASET})
public class EmployeeRepositoryTest {

    protected static final String DATASET = "classpath:datasets/employee-entries.xml";
    protected static final String EXPECTED_DATASET = "classpath:datasets/employee-afterInsert.xml";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    @Transactional
    public void findByFirstNameReturnsEmployees() {
        List<Employee> employees = employeeRepository.findByFirstName("John");
        assertNotNull(employees);
        assertThat(employees.size()).isGreaterThan(0);
        Employee employee = employees.get(0);
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void findByIdReturnsEmployee() {
        Employee employee = employeeRepository.findById(1);
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());

        Employee employee2 = employeeRepository.findById(4);
        assertNotNull(employee2);
        assertEquals("Sherlock", employee2.getFirstName());
        assertEquals("Holmes", employee2.getLastName());
    }

    @Test
    public void findAllReturnsAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(2, employees.size());
    }

    @Test
    @Ignore
    public void testFindByNull() {
        Employee employee = entityManager.find(Employee.class, null);
        assertEquals(employee, null);
    }

    @Test
    @Ignore
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = EXPECTED_DATASET, table = "employee")
    public void saveEmployeeShouldPersistEntity() {
        Employee employee = new Employee("Mycroft", "Holmes");
        entityManager.persist(employee);
    }
}
