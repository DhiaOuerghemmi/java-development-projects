package com.example.phr.auth;

import com.example.phr.auth.model.User;
import com.example.phr.auth.repository.UserRepository;
import com.example.phr.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    @Mock
    UserRepository repo;
    @Mock
    org.springframework.security.crypto.password.PasswordEncoder encoder;
    @InjectMocks
    UserService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_savesAndEncodes() {
        when(encoder.encode("raw")).thenReturn("enc");
        when(repo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        User u = service.register("user", "raw");
        assertThat(u.getPassword()).isEqualTo("enc");
        assertThat(u.getUsername()).isEqualTo("user");
    }
}
