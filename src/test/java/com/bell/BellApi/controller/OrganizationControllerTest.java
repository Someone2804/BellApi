package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnResultWithFilter() throws Exception{
        OrgFilter orgFilter = new OrgFilter("Bell", "777777777777", true);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/list").content(objectMapper.writeValueAsBytes(orgFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.data[*].name", containsInAnyOrder("Bell", "NotBell")))
                .andExpect(jsonPath("$.data[*].active", containsInAnyOrder(true, true)));
    }

    @Test
    public void shouldReturnResultWithId() throws Exception{
        mockMvc.perform(get("/api/organization/1"))
                .andDo(print())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Bell"))
                .andExpect(jsonPath("$.data.fullName").value("Bell Integrator"))
                .andExpect(jsonPath("$.data.inn").value("777777777777"))
                .andExpect(jsonPath("$.data.kpp").value("999999999"))
                .andExpect(jsonPath("$.data.address").value("br. r"))
                .andExpect(jsonPath("$.data.phone").isEmpty())
                .andExpect(jsonPath("$.data.active").value(true));
    }

    @Test
    public void shouldReturnSuccessAfterSave() throws Exception{
        OrganizationRequest organizationRequest = new OrganizationRequest(
                null,
                "SomeCompany",
                "SomeCompanyFullName",
                "76854567",
                "657465",
                "SomeCompanyAddress",
                "4995436432",
                true);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/add").content(objectMapper.writeValueAsBytes(organizationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data").value("successes"));
    }


}
