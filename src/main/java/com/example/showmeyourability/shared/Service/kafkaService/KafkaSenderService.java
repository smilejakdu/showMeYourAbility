package com.example.showmeyourability.shared.Service.kafkaService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSenderService {
    @KafkaListener(topics = "comment", groupId = "comment-group")
    public void listen(String message) {
        // poll 메서드를 사용하여 주기적으로 Kafka로 부터 메시지를 요청하고 가져옵니다.
        // 이 과정에서 listen 과 같은 개념이 사용될 수 있습니다.
        // Kafka에서는 listen 이라는 메서드 이름 대신 poll 이라는 용어를 사용합니다.
        System.out.println("Consumed message: " + message);
    }
}
