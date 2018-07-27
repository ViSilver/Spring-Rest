package com.example.springrestexample.repository.reactive;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.EmployeeRepository;
import io.reactivex.Flowable;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RxEmployeeRepository {

    private EmployeeRepository employeeRepository;

    @Inject
    public RxEmployeeRepository(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Flowable<Employee> findById(final Integer id){
        return Flowable.defer(() -> Flowable.just(employeeRepository.findById(id)));
    }

    public Flowable<Employee> findByFirstName(final String firstName){
        return Flowable.defer(() ->
                Flowable.fromIterable(employeeRepository.findByFirstName(firstName)));
    }

    public Flowable<Employee> findByAddress(final Address address){
        return Flowable.defer(() -> Flowable.just(employeeRepository.findByAddress(address)));
    }

    public void deleteByFirstName(final String firstName){
        employeeRepository.deleteByFirstName(firstName);
    }
}
