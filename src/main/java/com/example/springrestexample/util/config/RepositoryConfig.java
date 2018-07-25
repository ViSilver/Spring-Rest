package com.example.springrestexample.util.config;

import com.example.springrestexample.repository.reactive.RxRepositoryBasePackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages =
        {"com.example.springrestexample.repository", "com.example.springrestexample.entity"})
@ComponentScan(basePackageClasses = {RxRepositoryBasePackage.class})
public class RepositoryConfig {

}
