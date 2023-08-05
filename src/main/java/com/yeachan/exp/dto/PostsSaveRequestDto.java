package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Posts;
import com.yeachan.exp.service.utils.MarkdownUtils;
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
    private byte[] content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, byte[] content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public Posts toEntity() {
        return Posts.builder()
                .markDown(content)
                .content(MarkdownUtils.convertToTextContent(content))
                .author(author)
                .title(title)
                .build();
    }
}