package com.example.springrestexample.repository.reactive;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.EmployeeRepository;
import io.reactivex.Observable;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RxEmployeeRepository {

    private EmployeeRepository employeeRepository;

    @Inject
    public RxEmployeeRepository(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Observable<Employee> findById(final Integer id){
        return Observable.defer(() -> Observable.just(employeeRepository.findById(id)));
    }

    public Observable<Employee> findByFirstName(final String firstName){
        return Observable.defer(() -> Observable.fromIterable(employeeRepository.findByFirstName(firstName)));
    }

    public Observable<Employee> findByAddress(final Address address){
        return Observable.defer(() -> Observable.just(employeeRepository.findByAddress(address)));
    }

    public void deleteByFirstName(final String firstName){
        employeeRepository.deleteByFirstName(firstName);
    }
}
