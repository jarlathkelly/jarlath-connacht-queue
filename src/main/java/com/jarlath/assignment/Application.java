package com.jarlath.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages="com.jarlath")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
