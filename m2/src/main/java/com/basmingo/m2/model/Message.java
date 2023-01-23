package com.basmingo.m2.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Long id;
    private Integer sessionId;
    private LocalDateTime timeStampServiceOne;
    private LocalDateTime timeStampServiceTwo;
    private LocalDateTime timeStampServiceThree;
    private LocalDateTime endTimestamp;
}
