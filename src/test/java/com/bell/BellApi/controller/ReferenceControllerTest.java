package com.bell.BellApi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReferenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllDocumentNames() throws Exception{
        mockMvc.perform(get("/api/docs"))
                .andExpectAll(status().isOk(),
                        jsonPath("$.data[*].name").value("Паспорт Гражданина РФ"),
                        jsonPath("$.data[*].code").value("21"));
    }

    @Test
    public void shouldReturnAllCountries() throws Exception{
        mockMvc.perform(get("/api/countries"))
                .andExpectAll(status().isOk(),
                        jsonPath("$.data[*].name").value("Российская Федерация"),
                        jsonPath("$.data[*].code").value("643"));
    }
}
