package com.example.phr.record.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class PrometheusConfig {

    private final MeterRegistry registry;

    public PrometheusConfig(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void customizeMetrics() {
        registry.config().commonTags("service", "record-service");
    }
}
