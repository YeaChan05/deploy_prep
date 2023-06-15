package com.yeachan.exp.service;

import com.yeachan.exp.domain.Posts;
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
 * fileName : PostsServiceTest
 * author :  ShinYeaChan
 * date : 2023-06-14
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsServiceTest {
    
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
                .author("qkenrdl05@gmail.com")
                .content("테스트 본문")
                .title("테스트 타이틀")
                .build();
        
        //when
        postsService.save(dto);
        
        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
    
 }



 