package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnResultWithFilter() throws Exception{
        UserFilter userFilter = new UserFilter(1L,
                "Bell worker",
                null,
                null,
                "Java Senior",
                "21",
                "643");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/user/list").content(objectMapper.writeValueAsBytes(userFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data[*].id", containsInAnyOrder(1, 2)),
                jsonPath("$.data[*].firstName", containsInAnyOrder("Bell worker", "Bell worker2")),
                jsonPath("$.data[*].secondName", containsInAnyOrder(nullValue(), nullValue())),
                jsonPath("$.data[*].middleName", containsInAnyOrder(nullValue(), nullValue())),
                jsonPath("$.data[*].position[*]",
                        containsInAnyOrder("Java Middle", "Java Senior", "Java Senior")));
    }

    @Test
    public void shouldReturnResultWithId() throws Exception{
        mockMvc.perform(get("/api/user/1"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                jsonPath("$.data.id").value(1),
                jsonPath("$.data.firstName").value("Bell worker"),
                jsonPath("$.data.secondName").value(nullValue()),
                jsonPath("$.data.middleName").value(nullValue()),
                jsonPath("$.data.position", containsInAnyOrder("Java Middle", "Java Senior")),
                jsonPath("$.data.phone").value(nullValue()),
                jsonPath("$.data.docName").value("Паспорт Гражданина РФ"),
                jsonPath("$.data.docNumber").value("445365"),
                jsonPath("$.data.docDate").value("1999-12-22"),
                jsonPath("$.data.citizenshipName").value("Российская Федерация"),
                jsonPath("$.data.citizenshipCode").value("643"),
                jsonPath("$.data.isIdentified").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterSave() throws Exception{
        UserRequest userRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/user/add").content(objectMapper.writeValueAsBytes(userRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/user/5"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.data.id").value(5),
                        jsonPath("$.data.firstName").value("SomeWorkerFirstName"),
                        jsonPath("$.data.secondName").value("SomeWorkerSecondName"),
                        jsonPath("$.data.middleName").value("SomeWorkerMiddleName"),
                        jsonPath("$.data.position", containsInAnyOrder("Java Junior")),
                        jsonPath("$.data.phone").value("4953245243"),
                        jsonPath("$.data.docName").value("Паспорт Гражданина РФ"),
                        jsonPath("$.data.docNumber").value("2325435"),
                        jsonPath("$.data.docDate").value("1990-12-11"),
                        jsonPath("$.data.citizenshipName").value("Российская Федерация"),
                        jsonPath("$.data.citizenshipCode").value("643"),
                        jsonPath("$.data.isIdentified").value(true));
    }

    @Test
    public void shouldReturnSuccessAndResultShouldExistAfterUpdate() throws Exception{
        UserRequest userRequest = createRequestObject();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/user/update").content(objectMapper.writeValueAsBytes(userRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("successes"));
        mockMvc.perform(get("/api/user/2"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.data.id").value(2),
                        jsonPath("$.data.firstName").value("SomeWorkerFirstName"),
                        jsonPath("$.data.secondName").value("SomeWorkerSecondName"),
                        jsonPath("$.data.middleName").value("SomeWorkerMiddleName"),
                        jsonPath("$.data.position[*]", containsInAnyOrder("Java Junior", "Java Senior")),
                        jsonPath("$.data.phone").value("4953245243"),
                        jsonPath("$.data.docName").value("Паспорт Гражданина РФ"),
                        jsonPath("$.data.docNumber").value("2325435"),
                        jsonPath("$.data.docDate").value("1990-12-11"),
                        jsonPath("$.data.citizenshipName").value("Российская Федерация"),
                        jsonPath("$.data.citizenshipCode").value("643"),
                        jsonPath("$.data.isIdentified").value(true));
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationSave() throws Exception{
        officeIdValidation("add");
        firstNameValidation("add");
        positionValidation("add");
    }

    @Test
    public void shouldThrowExceptionIfDidNotPassValidationUpdate() throws Exception{
        idValidation("update");
        firstNameValidation("update");
        positionValidation("update");
    }

    @Test
    public void shouldThrowExceptionIfFilterDidNotPassValidation() throws Exception{
        OfficeFilter officeFilter = new OfficeFilter(1L, "Bell office", null, true);
        officeFilter.setOrgId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/office/list").content(objectMapper.writeValueAsBytes(officeFilter)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Missed required parameter orgId"));
    }

    private UserRequest createRequestObject() throws Exception{
        UserRequest userRequest = new UserRequest(
                2L,
                1L,
                "SomeWorkerFirstName",
                "SomeWorkerSecondName",
                "SomeWorkerMiddleName",
                "Java Junior",
                "4953245243",
                "Паспорт Гражданина РФ",
                "21",
                "2325435",
                new GregorianCalendar(1990, Calendar.DECEMBER, 11).getTime(),
                "643",
                true);
        return userRequest;
    }

    private ResultActions checkResult(String url, UserRequest officeRequest) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return mockMvc.perform(post("/api/user/" + url).content(objectMapper.writeValueAsBytes(officeRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void idValidation(String url) throws Exception{
        UserRequest userRequest = createRequestObject();
        userRequest.setId(null);
        checkResult(url, userRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter id"));
    }

    private void officeIdValidation(String url) throws Exception{
        UserRequest userRequest = createRequestObject();
        userRequest.setOfficeId(null);
        checkResult(url, userRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter officeId"));
    }

    private void firstNameValidation(String url) throws Exception{
        UserRequest userRequest = createRequestObject();
        userRequest.setFirstName(null);
        checkResult(url, userRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter firstName"));
    }

    private void positionValidation(String url) throws Exception{
        UserRequest userRequest = createRequestObject();
        userRequest.setPosition(null);
        checkResult(url, userRequest)
                .andExpect(jsonPath("$.error").value("Missed required parameter position"));
    }

}
