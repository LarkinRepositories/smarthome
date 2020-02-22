package ru.innopolis.telegramService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.telegram.telegrambots.ApiContextInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class TelegramServiceApp {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramServiceApp.class, args);
    }
}
