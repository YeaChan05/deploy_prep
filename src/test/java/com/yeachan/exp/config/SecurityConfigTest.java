package com.yeachan.exp.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * package :  com.yeachan.exp.config
 * fileName : SecurityConfigTest
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@SpringBootTest
class PasswordEncoderTest {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void testValidBCryptEncoding() {
        String password = "qweasd123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
    
        System.out.println("Original Password: " + password);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}