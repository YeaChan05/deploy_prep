package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Post;
import com.yeachan.exp.service.utils.MarkdownUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * package :  com.yeachan.exp.dto
 * fileName : PostsMainResponseDto
 * author :  ShinYeaChan
 * date : 2023-06-15
 */

@Getter
@AllArgsConstructor
public class PostsMainResponseDto {
    private Long id;
    private String title;
    private String modifiedDate;
    private String content;
    public static PostsMainResponseDto of(Post entity){
        return new PostsMainResponseDto(
                entity.getPostId(),
                entity.getTitle(),
                toStringDateTime(entity.getModifiedDate()),
                MarkdownUtils.convertToTextContent(entity.getMarkDown())
        );
    }
    
    private static String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
}
