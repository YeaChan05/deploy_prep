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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }
    
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::of)
                .collect(Collectors.toList());
    }
    
    public void deletePost(Long id){
        postsRepository.deleteById(id);
    }
    @Transactional
    public void fixPost(PostUpdateRequestDto dto) {
        postsRepository.updateModifiedDateAndTitleAndContentAndAuthorById(LocalDateTime.now(), dto.getTitle(), dto.getContent(), dto.getAuthor(), dto.getId());
    }
    
    public List<PostsMainResponseDto> getPosts() {
        List<Posts> posts = postsRepository.findAll().stream().sorted(Comparator.comparing(Posts::getModifiedDate)).toList();
        return posts.stream().map(PostsMainResponseDto::of).toList();
    }
}