package com.dhia.record_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "health_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private Integer systolic;

    @Column
    private Integer diastolic;

    @Column
    private Double weight;

    @Column(length = 255)
    private String allergies;
}