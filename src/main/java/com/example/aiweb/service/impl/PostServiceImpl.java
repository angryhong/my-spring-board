package com.example.aiweb.service.impl;
// ← 잘못된 import 제거: com.example.aiweb.repository.entity.Post
import com.example.aiweb.entity.Post;                   // ← 반드시 이 위치
import com.example.aiweb.dto.PostDto;
import com.example.aiweb.exception.ResourceNotFoundException;
import com.example.aiweb.repository.PostRepository;
import com.example.aiweb.service.PostService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository repo;
    public PostServiceImpl(PostRepository repo) { this.repo = repo; }
    @Override public List<PostDto> findAll() {
        return repo.findAll().stream()
                .map(p -> new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getCreatedAt()))
                .collect(Collectors.toList()); }
    @Override public PostDto findById(Long id) {
        Post p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다: " + id));
        return new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getCreatedAt()); }
    @Override @Transactional public PostDto create(PostDto dto) {
        Post p = new Post(); p.setTitle(dto.getTitle()); p.setContent(dto.getContent()); p.setAuthor(dto.getAuthor());
        Post saved = repo.save(p);
        return new PostDto(saved.getId(), saved.getTitle(), saved.getContent(), saved.getAuthor(), saved.getCreatedAt()); }
}