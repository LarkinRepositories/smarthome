package ru.innopolis.mqttService;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqttServiceApp {

        public static void main(String[] args) {
            SpringApplication.run(MqttServiceApp.class, args);
        }

}
