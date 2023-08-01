package com.yeachan.exp.api;

import com.yeachan.exp.dto.PostUpdateRequestDto;
import com.yeachan.exp.dto.PostsMainResponseDto;
import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package :  com.yeachan.exp.api
 * fileName : WebRestController
 * author :  ShinYeaChan
 * date : 2023-06-13
 */
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class WebRestController {
    private final PostsService postsService;
    
    @PostMapping
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsService.save(dto);
    }
    
    @PostMapping("/adjust")
    public void adjustPost(@RequestBody PostUpdateRequestDto dto){
        postsService.fixPost(dto);
    }
    
    @DeleteMapping("/{postId}")
    public void deletePosts(@PathVariable Long postId) {
        postsService.deletePost(postId);
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getList(){
        List<PostsMainResponseDto> posts=postsService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
}
