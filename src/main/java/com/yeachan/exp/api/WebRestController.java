package com.yeachan.exp.api;

import com.yeachan.exp.domain.Posts;
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
    public ResponseEntity<?> savePosts(@RequestBody PostsSaveRequestDto dto){
        Posts posts = postsService.savePost(dto);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
    @PostMapping("/adjust")
    public ResponseEntity<Posts> adjustPost(@RequestBody PostUpdateRequestDto dto){
        Posts posts = postsService.fixPost(dto);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePosts(@PathVariable Long postId) {
        Posts posts = postsService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getList(){
        List<PostsMainResponseDto> posts=postsService.findAllDesc();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
}
