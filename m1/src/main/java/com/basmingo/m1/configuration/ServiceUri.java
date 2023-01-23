package com.basmingo.m1.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceUri {
    M2_SERVICE_ACCEPT("http://m2:8081/accept"),
    M2_SERVICE_IS_RUNNING_STATUS("http://m2:8081/status"),
    M3_SERVICE_IS_RUNNING_STATUS("http://m3:8082/status");
    private String Uri;
}
