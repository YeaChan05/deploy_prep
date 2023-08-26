package com.yeachan.exp.service;

import com.yeachan.exp.domain.Post;
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

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Post savePost(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity());
    }
    
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::of)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public Post deletePost(Long id){
        Post post =  postsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        postsRepository.delete(post);
        postsRepository.flush();
        return post;
    }
    @Transactional
    public void fixPost(Long postId,PostUpdateRequestDto dto) {
        Post post = postsRepository.findById(postId).orElseThrow();
        post.updatePost(dto);
    }
    
    public PostDetailsResponseDto getSinglePost(Long postId) {
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new DataAccessResourceFailureException("Post not found"));
        byte[] markDown = post.getMarkDown();
        MarkdownUtils.convertToEncodedMarkdown(markDown);
        return new PostDetailsResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getAuthor(),
                post.getModifiedDate().toString(),
                markDown
        );
    }
    
}