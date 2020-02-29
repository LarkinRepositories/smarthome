package ru.innopolis.scenario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScenarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioServiceApplication.class, args);
    }

}
