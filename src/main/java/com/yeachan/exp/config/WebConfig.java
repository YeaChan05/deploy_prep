package com.yeachan.exp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * package :  com.yeachan.exp.config
 * fileName : WebConfig
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**CORS 설정
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts/all")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .maxAge(3600);
        
        registry.addMapping("/posts/publish")
                .allowedOrigins("http://localhost:3000")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .maxAge(3600);
        
        registry.addMapping("/posts/**")
                .allowedOrigins("http://localhost:3000")
                .allowedHeaders("*")
                .allowedMethods("GET","DELETE","POST")
                .maxAge(3600);
    
        registry.addMapping("/auth/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","DELETE","POST")
                .maxAge(3600);
        
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
