package com.example.springtestexample.repository;


import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.util.config.JavaConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JavaConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Sql({"classpath:sql/test-data.sql"})
    @Rollback
    @Ignore
    public void findByStreetReturnsAddress() {
        Address address = addressRepository.findByStreet("Stefan cel Mare");

        assertThat(address).isNotNull();
        assertThat(address.getCity()).isEqualTo("Chisinau");
        assertThat(address.getStreet()).isEqualTo("Stefan cel Mare");
        assertThat(address.getNumber()).isEqualTo("1");
    }
}
