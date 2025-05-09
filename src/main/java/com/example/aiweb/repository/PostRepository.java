package com.example.aiweb.repository;

import com.example.aiweb.entity.Member;
import com.example.aiweb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Member author);
    List<Post> findByTitleContaining(String keyword);
    List<Post> findByCategory(String category);
    List<Post> findAllByOrderByIdDesc();              // 최신순
    List<Post> findAllByOrderByViewCountDesc();       // 조회수순

}
