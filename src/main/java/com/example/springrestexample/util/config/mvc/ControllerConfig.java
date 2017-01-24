package com.example.springrestexample.util.config.mvc;

import com.example.springrestexample.controller.AddressController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Bean
    public AddressController addressController() {
        return new AddressController();
    }
}
