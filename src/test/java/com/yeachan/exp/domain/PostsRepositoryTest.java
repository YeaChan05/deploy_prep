package com.yeachan.exp.domain;

import com.yeachan.exp.repository.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * package :  com.yeachan.exp.domain
 * fileName : PostsTest
 * author :  ShinYeaChan
 * date : 2023-06-14
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    
    @Autowired
    PostsRepository postsRepository;
    
    @AfterEach
    void setUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글저장_불러오기() {
        //given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .author("qkenrdl05@gmail.com")
                .build());
        
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글"));
        
    }
    @Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .author("qkenrdl05@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}