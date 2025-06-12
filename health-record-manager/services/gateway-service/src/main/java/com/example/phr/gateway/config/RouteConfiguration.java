package com.example.phr.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.*;
import reactor.core.publisher.Mono;

@Configuration
public class RouteConfiguration {

    // Rate-limit by user IP
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
