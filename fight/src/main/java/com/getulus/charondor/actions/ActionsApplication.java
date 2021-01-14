package com.getulus.charondor.actions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ActionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionsApplication.class, args);
    }

}
