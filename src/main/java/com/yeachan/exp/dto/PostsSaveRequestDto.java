package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Member;
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
    @Builder
    public PostsSaveRequestDto(String title, String markDown) {
        this.title = title;
        this.markDown = markDown;
    }
    
    public Post toEntity(Member member) {
        return Post.builder()
                .markDown(markDown.getBytes())
                .title(title)
                .member(member)
                .build();
    }
}