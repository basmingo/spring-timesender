package com.basmingo.m1.controllers;

import com.basmingo.m1.model.Message;
import com.basmingo.m1.service.DurationChecker;
import com.basmingo.m1.service.ServiceHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class MainControllerV1 {

    private final ServiceHandler serviceHandler;
    private final DurationChecker durationChecker;

    @Autowired
    public MainControllerV1(ServiceHandler serviceHandler, DurationChecker durationChecker) {
        this.serviceHandler = serviceHandler;
        this.durationChecker = durationChecker;
    }

    @GetMapping("/start")
    public void start() {
        durationChecker.schedule();
        log.info("start application");
        serviceHandler.setAllServicesRunningStatus(true);

        Message message = new Message();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        message.setSessionId(serviceHandler.getSessionId(sessionId));
        message.setTimeStampServiceOne(LocalDateTime.now());
        serviceHandler.send(message);
    }

    @GetMapping("/stop")
    public void stop() {
        serviceHandler.setAllServicesRunningStatus(false);
        log.info("service stopped");
        log.info("generated {} messages", serviceHandler.getTotalMessagesCreated());
        log.info(LocalDateTime.now().toString());
    }

    @PostMapping("/messages")
    @SneakyThrows
    public void acceptMessage(@RequestBody Message message) {
        message.setEndTimestamp(LocalDateTime.now());
        message.setTimeStampServiceOne(LocalDateTime.now());
        log.info(message.getEndTimestamp().toString());
        serviceHandler.saveEntity(message.clone());
        serviceHandler.send(message);
    }
}
