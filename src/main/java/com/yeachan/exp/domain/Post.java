package com.yeachan.exp.domain;

import com.yeachan.exp.dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * package :  com.yeachan.exp.domain
 * fileName : Post
 * author :  ShinYeaChan
 * date : 2023-06-13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
@Builder
@AllArgsConstructor
public class Post extends BaseTimeEntity{
    
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long postId;
    
    @Column(length = 500, nullable = false)
    private String title;
    
    @Lob
    @Column(name = "mark_down", nullable = false)
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] markDown;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    
    public void updatePost(PostUpdateRequestDto dto) {
        super.updateModifiedDate();
        this.title=dto.getTitle();
        this.markDown= dto.getMarkDown().getBytes();
    }
}