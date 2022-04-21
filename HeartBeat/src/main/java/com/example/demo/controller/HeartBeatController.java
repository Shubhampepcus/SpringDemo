package com.example.demo.controller;

import com.example.demo.JavaConfig;
import com.example.demo.healthService.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {
    ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    HealthService healthService = context.getBean("getHealthService", HealthService.class);
    @GetMapping("/checkhealth")
    public String getHealth() {
        return healthService.checkHealth();

    }

}
