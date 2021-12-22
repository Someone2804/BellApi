package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnResultWithFilter() throws Exception{
        OfficeFilter officeFilter = new OfficeFilter(1L, "Bell office", null, true);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/office/list").content(objectMapper.writeValueAsBytes(officeFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data[*].id", containsInAnyOrder(1, 2)),
                jsonPath("$.data[*].name", containsInAnyOrder("Bell office1", "Bell office2")),
                jsonPath("$.data[*].isActive", containsInAnyOrder(true, true)));
    }

    @Test
    public void shouldReturnResultWithId() throws Exception{
        mockMvc.perform(get("/api/office/1"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(1),
                jsonPath("$.data.name").value("Bell office1"),
                jsonPath("$.data.address").value("qwerty"),
                jsonPath("$.data.phone").isEmpty(),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterSave() throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/office/add").content(objectMapper.writeValueAsBytes(officeRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/office/4"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(4),
                jsonPath("$.data.name").value("SomeOfficeFullName"),
                jsonPath("$.data.address").value("SomeOfficeAddress"),
                jsonPath("$.data.phone").value("657465"),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterUpdate() throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/office/update").content(objectMapper.writeValueAsBytes(officeRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/office/2"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(2),
                jsonPath("$.data.name").value("SomeOfficeFullName"),
                jsonPath("$.data.address").value("SomeOfficeAddress"),
                jsonPath("$.data.phone").value("657465"),
                jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationSave() throws Exception{
        orgIdValidation("add");
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationUpdate() throws Exception{
        idValidation("update");
        nameValidation("update");
        addressValidation("update");
    }

    @Test
    public void shouldThrowExceptionIfFilterDidNotPassValidation() throws Exception{
        OfficeFilter officeFilter = new OfficeFilter(1L, "Bell office", null, true);
        officeFilter.setOrgId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/office/list").content(objectMapper.writeValueAsBytes(officeFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isBadRequest(),
                jsonPath("$.error").value("Missed required parameter orgId"));
    }

    private OfficeRequest createRequestObject() throws Exception{
        OfficeRequest officeRequest = new OfficeRequest(
                2L,
                1L,
                "SomeOfficeFullName",
                "SomeOfficeAddress",
                "657465",
                true);
        return officeRequest;
    }

    private ResultActions checkResult(String url, OfficeRequest officeRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return mockMvc.perform(post("/api/office/" + url).content(objectMapper.writeValueAsBytes(officeRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void idValidation(String url) throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        officeRequest.setId(null);
        checkResult(url, officeRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter id"));
    }

    private void orgIdValidation(String url) throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        officeRequest.setOrgId(null);
        checkResult(url, officeRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter orgId"));
    }

    private void nameValidation(String url) throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        officeRequest.setName(null);
        checkResult(url, officeRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter name"));
    }

    private void addressValidation(String url) throws Exception{
        OfficeRequest officeRequest = createRequestObject();
        officeRequest.setAddress(null);
        checkResult(url, officeRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter address"));
    }

}
