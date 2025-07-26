package com.dhia.record_service.controller;

import com.dhia.record_service.model.HealthRecord;
import com.dhia.record_service.service.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class HealthRecordController {

    private final HealthRecordService service;

    @PostMapping
    public ResponseEntity<HealthRecord> create(@RequestBody HealthRecord record) {
        return ResponseEntity.ok(service.save(record));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<HealthRecord>> listByUser(@PathVariable String username) {
        return ResponseEntity.ok(service.findAllByUser(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}