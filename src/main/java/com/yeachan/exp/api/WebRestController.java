package com.yeachan.exp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package :  com.yeachan.exp.api
 * fileName : WebRestController
 * author :  ShinYeaChan
 * date : 2023-06-13
 */
@RestController
public class WebRestController {
    
    @GetMapping("/test")
    public String hello() {
        return "Hi";
    }
}
