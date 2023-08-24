package com.yeachan.exp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * package :  com.yeachan.exp.dto
 * fileName : TokenDto
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
}
