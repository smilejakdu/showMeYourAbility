package com.example.showmeyourability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude= KafkaAutoConfiguration.class)
public class ShowMeYourAbilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowMeYourAbilityApplication.class, args);
    }
}
