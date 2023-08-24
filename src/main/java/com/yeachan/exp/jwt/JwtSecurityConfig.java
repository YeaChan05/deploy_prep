package com.yeachan.exp.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * package :  com.yeachan.exp.domain.jwt
 * fileName : JwtSecurityConfig
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@RequiredArgsConstructor
@Slf4j
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter=new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
