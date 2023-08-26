package com.yeachan.exp.repository;

import com.yeachan.exp.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query("update Post p set p.modifiedDate = ?1, p.title = ?2, p.markDown = ?3 where p.id = ?4")
    void updateModifiedDateAndTitleAndContentById(LocalDateTime modifiedDate, String title, byte[] content, Long id);
//    @Modifying
//    @Query("update Post p set p.modifiedDate = ?1, p.title = ?2, p.content = ?3, p.author = ?4 where p.id = ?5")
//    Post updateModifiedDateAndTitleAndContentAndAuthorById(LocalDateTime modifiedDate, String title, String content, String author, Long id);
    

    @Query("SELECT p " +
            "FROM Post p " +
            "ORDER BY p.modifiedDate DESC")
    Stream<Post> findAllDesc();
}