package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Posts;
import lombok.*;

/**
 * package :  com.yeachan.exp.dto
 * fileName : PostsSaveRequestDto
 * author :  ShinYeaChan
 * date : 2023-06-14
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostsSaveRequestDto {
    
    private String title;
    private String markDown;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String markDown, String author) {
        this.title = title;
        this.markDown = markDown;
        this.author = author;
    }
    
    public Posts toEntity() {
        return Posts.builder()
                .markDown(markDown.getBytes())
                .author(author)
                .title(title)
                .build();
    }
}