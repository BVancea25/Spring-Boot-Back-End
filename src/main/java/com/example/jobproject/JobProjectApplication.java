package com.example.jobproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.jobproject.repository")
@SpringBootApplication
public class JobProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobProjectApplication.class, args);
    }

}
