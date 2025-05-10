package com.example.aiweb.service;
import com.example.aiweb.dto.ReplyDto;
import java.util.List;
public interface ReplyService {
    List<ReplyDto> findByPostId(Long postId);
    ReplyDto create(ReplyDto dto);
}