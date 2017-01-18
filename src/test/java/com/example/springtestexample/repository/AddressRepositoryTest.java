package com.example.springtestexample.repository;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.util.config.JavaConfig;
import com.example.springrestexample.util.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JavaConfig.class, TestConfig.class})
@ActiveProfiles("test")
@Sql({"classpath:sql/test-schema.sql", "classpath:sql/test-data.sql"})
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testFindAll() {
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    public void findByStreetReturnsAddress() {
        Address address = addressRepository.findByStreet("Stefan cel Mare");

        assertThat(address).isNotNull();
        assertThat(address.getCity()).isEqualTo("Chisinau");
        assertThat(address.getStreet()).isEqualTo("Stefan cel Mare");
        assertThat(address.getNumber()).isEqualTo("1");
    }
}
