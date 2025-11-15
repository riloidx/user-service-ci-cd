package com.innowise.userservice;

import com.innowise.userservice.config.CacheProperties;
import com.innowise.userservice.config.CardProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties({CardProperties.class, CacheProperties.class})
@EnableCaching
public class InnowiseUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnowiseUserServiceApplication.class, args);
    }

}
