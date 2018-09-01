package com.rathish.spring.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringIntegrationMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationMqApplication.class, args);
    }
}
