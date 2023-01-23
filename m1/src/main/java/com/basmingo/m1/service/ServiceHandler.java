package com.basmingo.m1.service;

import com.basmingo.m1.configuration.ServiceUri;
import com.basmingo.m1.model.Message;
import com.basmingo.m1.repository.MessageRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceHandler {
    @Getter
    private Integer totalMessagesCreated;
    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;
    private final Map<String, Integer> activeSessions;

    @Autowired
    public ServiceHandler(RestTemplate restTemplate, MessageRepository messageRepository) {
        this.restTemplate = restTemplate;
        this.messageRepository = messageRepository;
        activeSessions = new ConcurrentHashMap<>();
        totalMessagesCreated = 0;
    }

    public void send(Message message) {
        totalMessagesCreated++;
        restTemplate.postForLocation(ServiceUri.M2_SERVICE_ACCEPT.getUri(), message);
    }

    public void setAllServicesRunningStatus(Boolean status) {
        restTemplate.postForLocation(ServiceUri.M2_SERVICE_IS_RUNNING_STATUS.getUri(), status);
        restTemplate.postForLocation(ServiceUri.M3_SERVICE_IS_RUNNING_STATUS.getUri(), status);
    }

    public Integer getSessionId(String sessionId) {
        return activeSessions.computeIfAbsent(sessionId, k -> activeSessions.size() + 1);
    }

    public Optional<Message> saveEntity(Message message) {
        return Optional.of(messageRepository.save(message));
    }
}
