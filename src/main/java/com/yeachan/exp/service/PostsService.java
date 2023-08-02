package com.yeachan.exp.service;

import com.yeachan.exp.domain.Posts;
import com.yeachan.exp.dto.PostUpdateRequestDto;
import com.yeachan.exp.dto.PostsMainResponseDto;
import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Posts savePost(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity());
    }
    
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::of)
                .collect(Collectors.toList());
    }
    
    public Posts deletePost(Long id){
        Posts posts =  postsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        postsRepository.delete(posts);
        return posts;
    }
    @Transactional
    public Posts fixPost(PostUpdateRequestDto dto) {
        Posts posts = postsRepository.updateModifiedDateAndTitleAndContentAndAuthorById(LocalDateTime.now(), dto.getTitle(), dto.getContent(), dto.getAuthor(), dto.getId());
        return posts;
    }
}