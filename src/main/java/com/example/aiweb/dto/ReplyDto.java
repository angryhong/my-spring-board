package com.example.aiweb.dto;
import java.time.LocalDateTime;
public class ReplyDto {
    private Long id;
    private Long postId;
    private String commenter;
    private String text;
    private LocalDateTime commentedAt;
    public ReplyDto(Long id, Long postId, String commenter, String text, LocalDateTime commentedAt) {
        this.id = id;
        this.postId = postId;
        this.commenter = commenter;
        this.text = text;
        this.commentedAt = commentedAt;
    }
    public Long getId() { return id; }
    public Long getPostId() { return postId; }
    public String getCommenter() { return commenter; }
    public String getText() { return text; }
    public LocalDateTime getCommentedAt() { return commentedAt; }
}