package com.dhia.record_service;


import com.dhia.record_service.model.HealthRecord;
import com.dhia.record_service.repository.HealthRecordRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RecordIntegrationTest {

    @Container
    static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("recorddb")
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
    private HealthRecordRepository repo;

    @Test
    void createAndFetchRecord() {
        // Create
        HealthRecord rec = new HealthRecord(null, "alice", LocalDateTime.now(), 120, 80, 65.0, "None");
        ResponseEntity<HealthRecord> create = rest.postForEntity(
            "/api/records", rec, HealthRecord.class);
        assertThat(create.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Fetch
        ResponseEntity<HealthRecord[]> fetch = rest.getForEntity(
            "/api/records/alice", HealthRecord[].class);
        assertThat(fetch.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<HealthRecord> list = List.of(fetch.getBody());
        assertThat(list).hasSize(1)
                        .first()
                        .extracting(HealthRecord::getUsername)
                        .isEqualTo("alice");
    }
}
 {
    
}


import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;

