package com.dhia.record_service.service;


import com.dhia.record_service.model.HealthRecord;
import com.dhia.record_service.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordService {

    private final HealthRecordRepository repo;

    public HealthRecord save(HealthRecord record) {
        return repo.save(record);
    }

    public List<HealthRecord> findAllByUser(String username) {
        return repo.findByUsername(username);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
