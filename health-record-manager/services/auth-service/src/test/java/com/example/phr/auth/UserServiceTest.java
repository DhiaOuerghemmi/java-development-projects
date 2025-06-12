package com.example.phr.auth;

import com.example.phr.auth.model.User;
import com.example.phr.auth.repository.UserRepository;
import com.example.phr.auth.service.UserService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    @Mock
    UserRepository repo;
    @InjectMocks
    UserService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_savesUser() {
        // TODO: implement test
    }
}
