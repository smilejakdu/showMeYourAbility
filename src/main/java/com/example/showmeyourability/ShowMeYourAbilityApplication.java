package com.example.showmeyourability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케줄러를 사용하기 위해
@EnableJpaAuditing // 생성일자와 수정일짜를 자동으로 업데이트하게된다.
@SpringBootApplication
public class ShowMeYourAbilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowMeYourAbilityApplication.class, args);
    }
}
