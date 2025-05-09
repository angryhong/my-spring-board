package com.example.aiweb.repository;

import com.example.aiweb.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByPostId(Long postId); // 특정 글의 댓글만 가져오기
}
