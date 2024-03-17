package com.example.showmeyourability.shared.Service.kafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        System.out.println("sending message: " + message + " to topic: " + topic);
        // kafka의 지정된 topic으로 message를 전송한다.
        kafkaTemplate.send(topic, message);
    }
}
