package com.endava.springrestexample.util.serialization;


public class Views {

    public interface AddressPublicView {}

    public interface AddressPrivateView extends AddressPublicView {}

    public interface EmployeePublicView {}

    public interface EmployeePrivateView extends AddressPublicView, EmployeePublicView {}

    public interface AllPrivateView extends EmployeePrivateView, AddressPrivateView {}

}
