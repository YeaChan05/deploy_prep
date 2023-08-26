package com.yeachan.exp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * package :  com.yeachan.exp.dto
 * fileName : PostUpdateRequestDto
 * author :  ShinYeaChan
 * date : 2023-06-15
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateRequestDto {
    private String title;
    private String markDown;
}
