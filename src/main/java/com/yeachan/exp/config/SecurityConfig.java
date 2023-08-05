package com.yeachan.exp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * package :  com.yeachan.exp.config
 * fileName : SecurityConfig
 * author :  ShinYeaChan
 * date : 2023-08-01
 */
@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts/all")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET");
        
        registry.addMapping("/posts/publish")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST");
    
        registry.addMapping("/posts/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","DELETE");
        
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
