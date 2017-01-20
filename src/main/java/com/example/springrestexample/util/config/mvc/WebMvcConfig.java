package com.example.springrestexample.util.config.mvc;

import com.example.springrestexample.controller.ControllerPackage;
import com.example.springrestexample.util.serialization.HibernateAwareObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ControllerPackage.class})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        HibernateAwareObjectMapper hibernateAwareObjectMapper = new HibernateAwareObjectMapper();
        hibernateAwareObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonConverter.setObjectMapper(hibernateAwareObjectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(customJackson2HttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("home").setViewName("home");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }



}
