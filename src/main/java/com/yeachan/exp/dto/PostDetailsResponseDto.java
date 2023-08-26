package com.yeachan.exp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * package :  com.yeachan.exp.dto
 * fileName : PostDetailsResponseDto
 * author :  ShinYeaChan
 * date : 2023-08-04
 */
@Getter
@AllArgsConstructor
public class PostDetailsResponseDto implements Serializable {
    private Long id;
    private String title;
    private String modifiedDate;
    private byte[] markDown;
}
