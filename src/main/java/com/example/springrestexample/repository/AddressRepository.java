package com.example.springrestexample.repository;


import com.example.springrestexample.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findById(Integer id);

    Address findByStreet(String street);
}
