package com.basmingo.m2.util;

import com.basmingo.m2.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MessageSerializer implements Serializer<Message> {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, Message data) {
        if (data == null) {
            return null;
        }
        return objectMapper.writeValueAsBytes(data);
    }

    @Override
    public void close() {
    }
}
