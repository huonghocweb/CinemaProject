package com.poly.cinemaproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Configuration
public class ModelMapperConfig {
     @Bean
    ModelMapper modelMapper(){
         return new ModelMapper();
     }
}
