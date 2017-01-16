package com.endava.springrestexample.repository;


import com.endava.springrestexample.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findById(Integer id);
}
