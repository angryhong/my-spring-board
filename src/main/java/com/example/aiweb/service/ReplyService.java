package com.example.aiweb.service;

import com.example.aiweb.entity.Reply;
import com.example.aiweb.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void save(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> findByPostId(Long postId) {
        return replyRepository.findByPostId(postId);
    }

    public Reply findById(Long id) {
        return replyRepository.findById(id).orElse(null);
    }

    public void delete(Reply reply) {
        replyRepository.delete(reply);
    }
}
