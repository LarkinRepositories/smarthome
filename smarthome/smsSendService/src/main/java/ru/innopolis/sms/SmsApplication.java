package ru.innopolis.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Точка входа в приложение
 * @author Alina
 * пример вызова
 * http://localhost:8080/sendSMS?to=%2BXXXXXXXXXXX&message=Hello
 * http://localhost:8762/sms/sendSMS?to=%2BXXXXXXXXXXX&message=Hello
 * %2BXXXXXXXXXXX - номер телефона в международном формате (%2B-знак "+")
 */
@SpringBootApplication
@EnableSwaagger2
@EnableEurekaClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
