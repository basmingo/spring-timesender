package com.basmingo.m3.controller;

import com.basmingo.m3.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControllerV1 {
    private final MessageSender messageSender;

    @Autowired
    public MainControllerV1(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping("/status")
    public void setStatus(@RequestBody Boolean status) {
        messageSender.setIsRunning(status);
    }
}
