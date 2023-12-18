package com.loanorignx.app.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {

        return new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                                 .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                 .enable(SerializationFeature.INDENT_OUTPUT)
                                 .registerModule(new JavaTimeModule());
    }
}