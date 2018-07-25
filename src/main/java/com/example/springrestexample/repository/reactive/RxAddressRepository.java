package com.example.springrestexample.repository.reactive;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import io.reactivex.Observable;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RxAddressRepository {

    private AddressRepository addressRepository;

    @Inject
    public RxAddressRepository(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Observable<Address> findById(final Integer id){
        return Observable.defer(() ->
                Observable.just(addressRepository.findById(id)));
    }

    public Observable<Address> findByStreet(final String street){
        return Observable.defer(() ->
                Observable.just(addressRepository.findByStreet(street)));
    }

}
