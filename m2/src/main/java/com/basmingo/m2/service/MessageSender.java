package com.basmingo.m2.service;

import com.basmingo.m2.model.Message;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    @Setter
    private Boolean isRunning;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public MessageSender(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Message message) {
        if (isRunning) {
            kafkaTemplate.send("messageSender", message);
        }
    }
}
