package com.example.springrestexample.util.config;

import com.example.springrestexample.util.serialization.HibernateAwareObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.example.springrestexample")
@Import({DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class})
public class JavaConfig {

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        HibernateAwareObjectMapper hibernateAwareObjectMapper = new HibernateAwareObjectMapper();
        jsonConverter.setObjectMapper(hibernateAwareObjectMapper);
        return jsonConverter;
    }
}
