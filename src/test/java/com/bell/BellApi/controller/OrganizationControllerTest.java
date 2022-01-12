package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/create-schema.sql",
        "/create-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop-schema.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnResultWithFilter() throws Exception{
        OrgFilter orgFilter = new OrgFilter("Bell", "777777777777", true);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/list").content(objectMapper.writeValueAsBytes(orgFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data[*].id", containsInAnyOrder(1, 2)),
                jsonPath("$.data[*].name", containsInAnyOrder("Bell", "NotBell")),
                jsonPath("$.data[*].isActive", containsInAnyOrder(true, true)));
    }

    @Test
    public void shouldReturnResultWithId() throws Exception{
        mockMvc.perform(get("/api/organization/1"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(1),
                jsonPath("$.data.name").value("Bell"),
                jsonPath("$.data.fullName").value("Bell Integrator"),
                jsonPath("$.data.inn").value("777777777777"),
                jsonPath("$.data.kpp").value("999999999"),
                jsonPath("$.data.address").value("br. r"),
                jsonPath("$.data.phone").isEmpty(),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterSave() throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/add").content(objectMapper.writeValueAsBytes(organizationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/organization/3"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(3),
                jsonPath("$.data.name").value("SomeCompany"),
                jsonPath("$.data.fullName").value("SomeCompanyFullName"),
                jsonPath("$.data.inn").value("76854567"),
                jsonPath("$.data.kpp").value("657465"),
                jsonPath("$.data.address").value("SomeCompanyAddress"),
                jsonPath("$.data.phone").value("4995436432"),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterUpdate() throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/update").content(objectMapper.writeValueAsBytes(organizationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/organization/2"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(2),
                jsonPath("$.data.name").value("SomeCompany"),
                jsonPath("$.data.fullName").value("SomeCompanyFullName"),
                jsonPath("$.data.inn").value("76854567"),
                jsonPath("$.data.kpp").value("657465"),
                jsonPath("$.data.address").value("SomeCompanyAddress"),
                jsonPath("$.data.phone").value("4995436432"),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationSave() throws Exception{
        nameValidation("add");
        fullNameValidation("add");
        innValidation("add");
        kppValidation("add");
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationUpdate() throws Exception{
        idValidation("update");
        nameValidation("update");
        fullNameValidation("update");
        innValidation("update");
        kppValidation("update");
    }

    @Test
    public void shouldThrowExceptionIfFilterDidNotPassValidation() throws Exception{
        OrgFilter orgFilter = new OrgFilter("Bell", "777777777777", true);
        orgFilter.setName(null);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/organization/list").content(objectMapper.writeValueAsBytes(orgFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isBadRequest(),
                jsonPath("$.error").value("Missed required parameter name"));
    }

    private OrganizationRequest createRequestObject() throws Exception{
        OrganizationRequest organizationRequest = new OrganizationRequest(
                2L,
                "SomeCompany",
                "SomeCompanyFullName",
                "76854567",
                "657465",
                "SomeCompanyAddress",
                "4995436432",
                true);
        return organizationRequest;
    }

    private ResultActions checkResult(String url, OrganizationRequest organizationRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return mockMvc.perform(post("/api/organization/" + url).content(objectMapper.writeValueAsBytes(organizationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void idValidation(String url) throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        organizationRequest.setId(null);
        checkResult(url, organizationRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter id"));
    }

    private void nameValidation(String url) throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        organizationRequest.setName(null);
        checkResult(url, organizationRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter name"));
    }

    private void fullNameValidation(String url) throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        organizationRequest.setFullName(null);
        checkResult(url, organizationRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter fullName"));
    }
    private void innValidation(String url) throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        organizationRequest.setInn(null);
        checkResult(url, organizationRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter inn"));
    }
    private void kppValidation(String url) throws Exception{
        OrganizationRequest organizationRequest = createRequestObject();
        organizationRequest.setKpp(null);
        checkResult(url, organizationRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter kpp"));
    }



}
