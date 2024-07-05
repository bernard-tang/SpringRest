package com.example.accessingdatajpa.util.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all origins
            .allowedMethods("GET", "POST", "PUT", "DELETE", "FETCH", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .allowedOrigins("http://localhost:5173");
    }
}
