package com.innowise.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "cache")
@Data
public class CacheProperties {
    private Map<String, Duration> ttl = new HashMap<>();
}
