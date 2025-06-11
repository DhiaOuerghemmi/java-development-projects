package com.example.phr.record.repository;

import com.example.phr.record.model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByUsername(String username);
}
