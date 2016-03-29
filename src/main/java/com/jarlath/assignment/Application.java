package com.jarlath.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The {@link Application} is the entry point for this Work Order Queue application.
 *
 * @author  Jarlath Kelly
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages="com.jarlath")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
