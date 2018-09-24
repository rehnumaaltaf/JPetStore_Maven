package com.olam.score.common.config;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.olam.score.common.util.DefaultJsonDeserializer;
import com.olam.score.common.util.DefaultJsonSerializer;
import com.olam.score.common.util.XSSFilter;

@Configuration 
public class DefaultWebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
    }
    @Bean
    public HttpMessageConverter<?> jsonConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new DefaultJsonDeserializer());
        module.addSerializer(String.class, new DefaultJsonSerializer());
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.registerModule(module);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XSSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("XSSFilter");
        registration.setOrder(20);
        return registration;
    } 
}