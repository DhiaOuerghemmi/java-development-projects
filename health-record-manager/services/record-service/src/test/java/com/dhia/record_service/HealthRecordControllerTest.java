package com.dhia.record_service;

import com.dhia.record_service.controller.HealthRecordController;
import com.dhia.record_service.model.HealthRecord;
import com.dhia.record_service.service.HealthRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HealthRecordController.class)
class HealthRecordControllerTest {
    @Autowired MockMvc mvc;
    @MockBean HealthRecordService service;
    @InjectMocks HealthRecordController healthRecordController;

    @Test void listByUser_returnsRecords() throws Exception {
        var record = new HealthRecord(1L, "alice", LocalDateTime.now(), 120, 80, 70.5, "None");
        when(service.findAllByUser("alice")).thenReturn(List.of(record));

        mvc.perform(get("/api/records/alice"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].username").value("alice"));
    }
}