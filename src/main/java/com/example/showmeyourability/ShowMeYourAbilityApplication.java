package com.example.showmeyourability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShowMeYourAbilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowMeYourAbilityApplication.class, args);
    }
//    zoned 데이터
//    mysql -> validate
//
}
