package com.yeachan.exp.service;

import com.yeachan.exp.domain.Member;
import com.yeachan.exp.domain.Post;
import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.repository.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * package :  com.yeachan.exp.service
 * fileName : PostServiceTest
 * author :  ShinYeaChan
 * date : 2023-06-14
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {
    
    @Autowired
    private PostsService postsService;
    
    @Autowired
    private PostsRepository postsRepository;
    
    
    @AfterEach
    void setUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void Dto데이터가_posts테이블에_저장된다 () {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .markDown(String.valueOf("테스트 본문".getBytes()))
                .title("테스트 타이틀")
                .build();
        
        //when
        postsService.savePost(dto,new Member());
        
        //then
        Post post = postsRepository.findAll().get(0);
        assertThat(post.getMarkDown()).isEqualTo(dto.getMarkDown());
        assertThat(post.getTitle()).isEqualTo(dto.getTitle());
    }
    
 }



 