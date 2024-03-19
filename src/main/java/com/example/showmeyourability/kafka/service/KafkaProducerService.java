package com.example.showmeyourability.kafka.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자를 통한 주입을 위한 어노테이션
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void sendMessage(
            String topics,
            String message
    ) {
        System.out.println("sending message: " + message + " to topic: " + topics);
        // kafka의 지정된 topic으로 message를 전송한다.
        kafkaTemplate.send(topics, message);
    }
}
