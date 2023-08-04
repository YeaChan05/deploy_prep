package com.yeachan.exp.dto;

import com.yeachan.exp.domain.Posts;
import com.yeachan.exp.service.MarkdownUtils;
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
    private String author;
    private String modifiedDate;
    private String content;
    public static PostsMainResponseDto of(Posts entity){
        return new PostsMainResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
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
