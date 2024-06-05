package com.car.management.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {

    @Bean
    public ModelMapper getmodelMapper()
    {
        return new ModelMapper();
    }
}
