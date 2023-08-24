package com.yeachan.exp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * package :  com.yeachan.exp.dto
 * fileName : LoginDto
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Email
    private String email;
    
    @NotNull
    @Size(min = 3,max = 100)
    private String password;
}
