package com.example.phr.auth;

import com.example.phr.auth.controller.AuthController;
import com.example.phr.auth.service.UserService;
import com.example.phr.auth.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    UserService userService;
    @MockBean
    JwtUtil jwtUtil;

    @Test
    void signup_returnsOk() throws Exception {
        when(userService.register(any(), any())).thenReturn(null);
        mvc.perform(post("/api/auth/signup")
                .param("username", "u")
                .param("password", "p"))
                .andExpect(status().isOk());
    }
}
