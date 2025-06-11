package com.example.phr.record.service;

import com.example.phr.record.model.HealthRecord;
import com.example.phr.record.repository.HealthRecordRepository;
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

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
