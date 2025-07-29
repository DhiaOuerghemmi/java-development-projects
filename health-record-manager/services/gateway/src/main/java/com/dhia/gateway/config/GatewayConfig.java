package com.dhia.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth_route", r -> r.path("/api/auth/**")
                                         .uri("http://auth-service:80"))
            .route("record_route", r -> r.path("/api/records/**")
                                           .uri("http://record-service:80"))
            .build();
    }
}
