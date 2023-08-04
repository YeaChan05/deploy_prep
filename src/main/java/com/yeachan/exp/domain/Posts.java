package com.yeachan.exp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * package :  com.yeachan.exp.domain
 * fileName : Posts
 * author :  ShinYeaChan
 * date : 2023-06-13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
@Builder
@AllArgsConstructor
public class Posts extends BaseTimeEntity{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = true)
    private String content;
    
    private String author;
    
    @Lob
    @Column(name = "mark_down", nullable = false)
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] markDown;
    
}