package com.example.demo;

import com.example.demo.healthService.HealthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HeartBeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartBeatApplication.class, args);
		HealthService h = new HealthService();
		System.out.println(h.checkHealth());;

	}

}
