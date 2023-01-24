package com.basmingo.m1;

import com.basmingo.m1.repository.MessageRepository;
import com.basmingo.m1.service.ServiceHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
public class ServiceHandlerTest {
    @MockBean
    RestTemplate restTemplate;
    @MockBean
    MessageRepository messageRepository;
    ServiceHandler serviceHandler;

    @Test
    public void serviceCreationTest() {
        given(restTemplate.postForLocation(anyString(), anyBoolean())).willReturn(URI.create("ok"));
        serviceHandler = new ServiceHandler(restTemplate, messageRepository);
        serviceHandler.setAllServicesRunningStatus(false);
        assertFalse(serviceHandler.getIsRunning());

        serviceHandler.setAllServicesRunningStatus(false);
        assertFalse(serviceHandler.getIsRunning());

        IntStream.range(1, 5)
                .forEach(x -> IntStream.range(1, 10)
                        .forEach(y -> serviceHandler.getAndUpdateSessionId(String.valueOf(x))));
        IntStream.range(1, 10)
                .forEach(x -> assertEquals(x, serviceHandler.getAndUpdateSessionId(String.valueOf(x))));
    }
}
