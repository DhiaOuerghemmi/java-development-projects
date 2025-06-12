package com.example.phr.auth;

import com.example.phr.auth.controller.AuthController;
import com.example.phr.auth.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @MockBean
    UserService userService;
    // TODO: write tests for signup/login endpoints
}