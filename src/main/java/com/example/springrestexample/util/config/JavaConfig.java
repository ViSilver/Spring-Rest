package com.example.springrestexample.util.config;

import com.example.springrestexample.util.config.mvc.WebMvcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.example.springrestexample")
@Import({DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class, WebMvcConfig.class})
public class JavaConfig {

}
