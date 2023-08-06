package com.yeachan.exp.service;

import com.yeachan.exp.domain.Posts;
import com.yeachan.exp.dto.PostDetailsResponseDto;
import com.yeachan.exp.dto.PostUpdateRequestDto;
import com.yeachan.exp.dto.PostsMainResponseDto;
import com.yeachan.exp.dto.PostsSaveRequestDto;
import com.yeachan.exp.repository.PostsRepository;
import com.yeachan.exp.service.utils.MarkdownUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
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
    
    @Transactional
    public Posts deletePost(Long id){
        Posts posts =  postsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        postsRepository.delete(posts);
        postsRepository.flush();
        return posts;
    }
    @Transactional
    public void fixPost(PostUpdateRequestDto dto) {
//        Posts posts = postsRepository.updateModifiedDateAndTitleAndContentAndAuthorById(LocalDateTime.now(), dto.getTitle(), dto.getContent(),  dto.getId());
        postsRepository.updateModifiedDateAndTitleAndContentById(LocalDateTime.now(), dto.getTitle(), dto.getContent().getBytes(), dto.getId());
    }
    
    public PostDetailsResponseDto getSinglePost(Long postId) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new DataAccessResourceFailureException("Post not found"));
        byte[] markDown = post.getMarkDown();
        MarkdownUtils.convertToEncodedMarkdown(markDown);
        return new PostDetailsResponseDto(
                post.getId(),
                post.getTitle(),
                post.getAuthor(),
                post.getModifiedDate().toString(),
                markDown
        );
    }
    
}