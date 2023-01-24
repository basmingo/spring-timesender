package com.basmingo.m1.controllers;

import com.basmingo.m1.exceptions.ServiceAlreadyRanOrStopped;
import com.basmingo.m1.model.Message;
import com.basmingo.m1.service.DurationChecker;
import com.basmingo.m1.service.ServiceHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<String> start() {
        if (serviceHandler.getIsRunning().equals(Boolean.TRUE)) {
            throw new ServiceAlreadyRanOrStopped();
        }

        durationChecker.schedule();
        log.info("start application");
        serviceHandler.setAllServicesRunningStatus(true);

        Message message = new Message();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        message.setSessionId(serviceHandler.getAndUpdateSessionId(sessionId));
        message.setTimeStampServiceOne(LocalDateTime.now());

        serviceHandler.send(message);
        return ResponseEntity.ok("Application started");
    }

    @GetMapping("/stop")
    public ResponseEntity<String> stop() {
        if (serviceHandler.getIsRunning().equals(Boolean.FALSE)) {
            throw new ServiceAlreadyRanOrStopped();
        }

        serviceHandler.setAllServicesRunningStatus(false);
        log.info("service stopped");
        log.info("generated {} messages", serviceHandler.getTotalMessagesCreated());
        log.info(LocalDateTime.now().toString());
        return ResponseEntity.ok("Application stopped");
    }

    @PostMapping("/messages")
    @SneakyThrows
    public ResponseEntity<String> acceptMessage(@RequestBody Message message) {
        message.setEndTimestamp(LocalDateTime.now());
        message.setTimeStampServiceOne(LocalDateTime.now());
        log.info(message.getEndTimestamp().toString());

        serviceHandler.saveEntity(message.clone());
        serviceHandler.send(message);
        return ResponseEntity.ok("Message handled");
    }

    @ExceptionHandler(ServiceAlreadyRanOrStopped.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> incorrectRequest() {
        return ResponseEntity.internalServerError().body("Internal error");
    }
}
