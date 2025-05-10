package com.example.aiweb.dto;
import java.time.LocalDateTime;
public class MemberDto {
    private Long id;
    private String username;
    private String displayName;
    private LocalDateTime joinedAt;
    public MemberDto(Long id, String username, String displayName, LocalDateTime joinedAt) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.joinedAt = joinedAt;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getDisplayName() { return displayName; }
    public LocalDateTime getJoinedAt() { return joinedAt; }
}