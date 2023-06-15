package com.yeachan.exp.api;

import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * package :  com.yeachan.exp.api
 * fileName : WebRestController
 * author :  ShinYeaChan
 * date : 2023-06-13
 */
@RestController
@RequiredArgsConstructor
public class WebRestController {
    private final PostsService postsService;
    @GetMapping("/test")
    public String hello() {
        return "Hi";
    }
    
    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsService.save(dto);
    }
}
