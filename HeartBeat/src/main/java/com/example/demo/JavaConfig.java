package com.example.demo;

import com.example.demo.healthService.HealthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.demo.healthService")
public class JavaConfig {


}
