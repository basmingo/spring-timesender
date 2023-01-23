package com.basmingo.m3.service;

import com.basmingo.m3.model.Message;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class MessageSender {

    @Setter
    private Boolean isRunning;
    private static final String M1_SERVICE_URI = "http://m1:8080/messages";
    private final RestTemplate restTemplate;

    @Autowired
    public MessageSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = "messageSender", groupId = "1")
    public void acceptAndSend(Message message) {
        if (isRunning) {
            message.setTimeStampServiceThree(LocalDateTime.now());
            restTemplate.postForLocation(M1_SERVICE_URI, message);
        }
    }
}
