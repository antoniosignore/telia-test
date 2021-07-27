package com.example.test.controller;

import com.example.test.beans.UserValueBean;
import com.example.test.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void test_create_user_by_rest_post() throws Exception {

        // Given
        UserValueBean userValueBean = new UserValueBean();
        userValueBean.setUsername("mickey");
        userValueBean.setLastname("mouse");

        // When
        when(userService.create(eq(userValueBean))).thenReturn(userValueBean);

        // Then
        final String content = objectMapper.writeValueAsString(userValueBean);

        MvcResult mvcResult = this.mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        UserValueBean response = objectMapper.readValue(content, UserValueBean.class);

        assertEquals("Not as expected", userValueBean, response);
    }

    @Test
    public void test_get_user_by_rest_get() throws Exception {

        // Given
        UserValueBean userValueBean = new UserValueBean();
        userValueBean.setUsername("mickey");
        userValueBean.setLastname("mouse");

        List<UserValueBean> userValueBeans = new ArrayList<>();
        userValueBeans.add(userValueBean);

        // When
        when(userService.findAll()).thenReturn(userValueBeans);

        // Then
        MvcResult mvcResult = this.mockMvc.perform(
                get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        log.info("test_get_user_by_rest_get::content : {}", content);
        List<UserValueBean> response = objectMapper.readValue(content,new TypeReference<List<UserValueBean>>() { });

        assertEquals("Not as expected", userValueBeans, response);

    }

    @Test
    public void test_get_one_user_by_id_rest_get() throws Exception {

        // Given
        UserValueBean userValueBean = new UserValueBean();
        userValueBean.setUsername("mickey");
        userValueBean.setLastname("mouse");

        // When
        when(userService.find(anyInt())).thenReturn(userValueBean);

        // Then
        MvcResult mvcResult = this.mockMvc.perform(
                get("/api/users/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        log.info("test_get_user_by_rest_get::content : {}", content);
        UserValueBean response = objectMapper.readValue(content,UserValueBean.class);

        assertEquals("Not as expected", userValueBean, response);

    }
}
