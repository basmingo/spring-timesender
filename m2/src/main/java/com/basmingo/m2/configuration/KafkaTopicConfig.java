package com.basmingo.m2.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    private final ApplicationConfig applicationConfig;
    private final String bootstrapAddress;

    @Autowired
    public KafkaTopicConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
        bootstrapAddress = applicationConfig.getBootstrapServers();
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(parameters);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("messageSender", 1, (short) 1);
    }
}
