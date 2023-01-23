package com.basmingo.m2.controller;

import com.basmingo.m2.model.Message;
import com.basmingo.m2.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MainControllerV1 {

    private final MessageSender messageSender;

    @Autowired
    public MainControllerV1(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping("/accept")
    public void acceptMessage(@RequestBody Message message) {
        message.setTimeStampServiceTwo(LocalDateTime.now());
        messageSender.send(message);
    }

    @PostMapping("/status")
    public void setStatus(@RequestBody Boolean status) {
        messageSender.setIsRunning(status);
    }
}
