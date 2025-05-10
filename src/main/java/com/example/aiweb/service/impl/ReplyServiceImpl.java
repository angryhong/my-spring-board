package com.example.aiweb.service.impl;

import com.example.aiweb.dto.ReplyDto;
import com.example.aiweb.entity.Reply;
import com.example.aiweb.repository.ReplyRepository;
import com.example.aiweb.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository repo;
    public ReplyServiceImpl(ReplyRepository repo) { this.repo = repo; }

    @Override
    public List<ReplyDto> findByPostId(Long postId) {
        return repo.findAll().stream()
                .filter(r -> r.getPostId().equals(postId))
                .map(r -> new ReplyDto(
                        r.getId(), r.getPostId(), r.getCommenter(), r.getText(), r.getCommentedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReplyDto create(ReplyDto dto) {
        Reply r = new Reply();
        r.setPostId(dto.getPostId());
        r.setCommenter(dto.getCommenter());
        r.setText(dto.getText());
        Reply saved = repo.save(r);
        return new ReplyDto(saved.getId(), saved.getPostId(), saved.getCommenter(), saved.getText(), saved.getCommentedAt());
    }
}