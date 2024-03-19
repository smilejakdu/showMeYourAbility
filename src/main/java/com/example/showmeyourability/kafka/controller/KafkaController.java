package com.example.showmeyourability.kafka.controller;

import com.example.showmeyourability.kafka.service.KafkaProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Tag(name = "kafka", description = "kafka API")
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "kafka message 보내기", description = "kafka message 보내기")
    public String sendMessage(@RequestBody Map<String, String> payload) {
        String topics = payload.get("topics");
        String message = payload.get("message");
        System.out.println("topics: " + topics);
        System.out.println("message: " + message);
        kafkaProducerService.sendMessage(topics, message);
        return "Message sent successfully";
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "kafka test", description = "kafka test")
    public String test() {
        return "test";
    }
}