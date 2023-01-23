package com.basmingo.m3.util;

import com.basmingo.m3.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class MessageDeserializer implements Deserializer<Message> {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    @SneakyThrows
    public Message deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        return objectMapper.readValue(new String(data, "UTF-8"), Message.class);
    }

    @Override
    public void close() {
    }
}
