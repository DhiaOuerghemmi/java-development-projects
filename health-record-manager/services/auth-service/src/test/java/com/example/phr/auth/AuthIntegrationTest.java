package com.example.phr.auth;


import com.example.phr.auth.model.User;
import com.example.phr.auth.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AuthIntegrationTest {

    @Container
    static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("authdb")
        .withUsername("sa")
        .withPassword("sa");

    @DynamicPropertySource
    static void databaseProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pg::getJdbcUrl);
        registry.add("spring.datasource.username", pg::getUsername);
        registry.add("spring.datasource.password", pg::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private UserRepository users;

    @Test
    void signupAndLoginFlow() {
        // Signup
        ResponseEntity<User> signup = rest.postForEntity(
            "/api/auth/signup?username=test&password=pw", null, User.class);
        assertThat(signup.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(users.findByUsername("test")).isPresent();

        // Login
        ResponseEntity<String> login = rest.postForEntity(
            "/api/auth/login?username=test&password=pw", null, String.class);
        assertThat(login.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(login.getBody()).isNotBlank();
    }
}
