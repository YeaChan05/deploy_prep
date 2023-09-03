package com.yeachan.exp.api;

import com.yeachan.exp.domain.Member;
import com.yeachan.exp.domain.Post;
import com.yeachan.exp.dto.PostDetailsResponseDto;
import com.yeachan.exp.dto.PostUpdateRequestDto;
import com.yeachan.exp.dto.PostsMainResponseDto;
import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.service.MemberService;
import com.yeachan.exp.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final MemberService memberService;
    
    @PostMapping("/publish")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> savePosts(@RequestBody PostsSaveRequestDto dto){
        Member member = memberService.getMyMemberWithAuthorities().orElseThrow();
        postsService.savePost(dto,member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PostMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<?> adjustPost(@PathVariable Long postId,@RequestBody PostUpdateRequestDto dto){
        postsService.fixPost(postId,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @DeleteMapping("/{postId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> deletePosts(@PathVariable Long postId) {
        try{
            Post post = postsService.deletePost(postId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<?> getSinglePosts(@PathVariable Long postId) {
        try{
            PostDetailsResponseDto posts = postsService.getSinglePost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getList(){
        List<PostsMainResponseDto> posts=postsService.findAllDesc();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
}
