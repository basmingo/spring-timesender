package com.basmingo.m1.service;

import com.basmingo.m1.configuration.ApplicationConfig;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class DurationChecker {
    private final ServiceHandler serviceHandler;
    private final Timer timer;
    private final Long duration;

    public DurationChecker(ApplicationConfig applicationConfig, ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
        duration = applicationConfig.getDuration();
        timer = new Timer("Timer");
    }

    public void schedule() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                serviceHandler.setAllServicesRunningStatus(false);
            }
        };
        timer.schedule(timerTask, duration);
    }
}
