package com.yeachan.exp.repository;

import com.yeachan.exp.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Modifying
    @Query("update Posts p set p.modifiedDate = ?1, p.title = ?2, p.content = ?3, p.author = ?4 where p.id = ?5")
    Posts updateModifiedDateAndTitleAndContentAndAuthorById(LocalDateTime modifiedDate, String title, String content, String author, Long id);
    

    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.modifiedDate DESC")
    Stream<Posts> findAllDesc();
}