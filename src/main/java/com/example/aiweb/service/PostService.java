package com.example.aiweb.service;

import com.example.aiweb.entity.Member;
import com.example.aiweb.entity.Post;
import com.example.aiweb.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  // ← 이게 없으면 DB에 반영 안됨!
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // PostService.java
    public void save(Post post) {
        postRepository.save(post);  // ← 이게 실제로 있어야 반영됨
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void delete(Post post) {
        postRepository.delete(post);  // ← 이거 없으면 삭제 안 됨!
    }

    public List<Post> findByAuthor(Member author) {
        return postRepository.findByAuthor(author);
    }

    public List<Post> searchByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public List<Post> findByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    public List<Post> sortByLatest() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public List<Post> sortByViewCount() {
        return postRepository.findAllByOrderByViewCountDesc();
    }




}
