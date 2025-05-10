package com.example.aiweb.service;
import com.example.aiweb.dto.PostDto;
import java.util.List;
public interface PostService {
    List<PostDto> findAll();
    PostDto findById(Long id);
    PostDto create(PostDto dto);
}