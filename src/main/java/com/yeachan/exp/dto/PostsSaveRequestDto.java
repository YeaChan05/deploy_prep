package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Post;
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
    
    public Post toEntity() {
        return Post.builder()
                .markDown(markDown.getBytes())
                .author(author)
                .title(title)
                .build();
    }
}