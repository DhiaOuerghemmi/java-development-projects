package com.dhia.record_service;

import com.dhia.record_service.model.HealthRecord;
import com.dhia.record_service.service.HealthRecordService;
import com.dhia.record_service.repository.HealthRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthRecordServiceTest {

    @Mock HealthRecordRepository repo;
    @InjectMocks HealthRecordService service;

    @BeforeEach void init() { MockitoAnnotations.openMocks(this); }

    @Test void save_and_findAllByUser() {
        var hr = new HealthRecord(null, "bob", LocalDateTime.now(), 115, 75, 68.2, "Peanuts");
        when(repo.save(hr)).thenReturn(hr);
        when(repo.findByUsername("bob")).thenReturn(List.of(hr));

        var saved = service.save(hr);
        assertThat(saved).isEqualTo(hr);

        var list = service.findAllByUser("bob");
        assertThat(list).containsExactly(hr);
    }
}