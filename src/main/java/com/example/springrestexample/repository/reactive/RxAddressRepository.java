package com.example.springrestexample.repository.reactive;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class RxAddressRepository {

    private AddressRepository addressRepository;

    @Inject
    public RxAddressRepository(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Flowable<Address> findById(final Integer id){
        return Flowable.defer(() ->
                Flowable.just(addressRepository.findById(id)))
                .subscribeOn(Schedulers.computation());
    }

    public Flowable<Address> findByStreet(final String street){
        return Flowable.defer(() ->
                Flowable.just(addressRepository.findByStreet(street)));
    }

}
