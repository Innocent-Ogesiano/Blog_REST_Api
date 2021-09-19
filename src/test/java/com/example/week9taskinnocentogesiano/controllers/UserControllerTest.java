package com.example.week9taskinnocentogesiano.controllers;

import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserServices userServices;

    @BeforeEach
    void setUp() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
//        this.userList.add(new User(2L, "user2@gmail.com", "pwd2","User2"));
//        this.userList.add(new User(3L, "user3@gmail.com", "pwd3","User3"));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void registerNewUser() {
    }

    @Test
    void userLogin() {
    }

    @Test
    void editUserProfile() {
    }
}