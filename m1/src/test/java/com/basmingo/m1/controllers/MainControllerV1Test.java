package com.basmingo.m1.controllers;

import com.basmingo.m1.service.DurationChecker;
import com.basmingo.m1.service.ServiceHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainControllerV1.class)
class MainControllerV1Test {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServiceHandler serviceHandler;
    @MockBean
    DurationChecker durationChecker;

    @Test
    public void startService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/start"))
                .andExpect(status().isOk());

        given(serviceHandler.getIsRunning()).willReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/start"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void stopService() throws Exception {
        given(serviceHandler.getIsRunning()).willReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/stop"))
                .andExpect(status().isOk());

        given(serviceHandler.getIsRunning()).willReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.get("/stop"))
                .andExpect(status().is5xxServerError());
    }
}