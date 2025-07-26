package com.dhia.record_service.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        // Spring Boot auto-configures based on application.properties
        return DataSourceBuilder.create().build();
    }
}