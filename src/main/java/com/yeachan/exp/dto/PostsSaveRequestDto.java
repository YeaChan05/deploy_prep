package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * package :  com.yeachan.exp.dto
 * fileName : PostsSaveRequestDto
 * author :  ShinYeaChan
 * date : 2023-06-14
 */
@Getter
@Setter

@NoArgsConstructor
public class PostsSaveRequestDto {
    
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}