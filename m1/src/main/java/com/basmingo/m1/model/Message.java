package com.basmingo.m1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

@Entity
@Data
public class Message implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "MC1_timestamp")
    private LocalDateTime timeStampServiceOne;

    @Column(name = "MC2_timestamp")
    private LocalDateTime timeStampServiceTwo;

    @Column(name = "MC3_timestamp")
    private LocalDateTime timeStampServiceThree;

    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;

    @Override
    @SneakyThrows
    public Message clone() {
        return (Message) super.clone();
    }
}
