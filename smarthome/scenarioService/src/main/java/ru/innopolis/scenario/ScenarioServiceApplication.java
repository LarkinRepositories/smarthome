package ru.innopolis.scenario;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class ScenarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScenarioServiceApplication.class, args);
    }

}
