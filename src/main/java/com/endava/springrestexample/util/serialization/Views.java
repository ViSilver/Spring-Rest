package com.endava.springrestexample.util.serialization;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Views {
    public static class Public {}

    public static class Private extends Public {}

    @JsonIgnoreProperties({"id", "street", "number"})
    public static class EmployeePublicView extends Private {}
}
