package com.example.aiweb.dto;

import java.time.LocalDateTime;

public class MemberDto {
    private Long id;
    private String username;
    private String password;      // ← 추가!
    private String displayName;
    private LocalDateTime joinedAt;

    // 생성자에 password 파라미터도 포함
    public MemberDto(Long id, String username, String password, String displayName, LocalDateTime joinedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.joinedAt = joinedAt;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }           // ← 추가!
    public String getDisplayName() { return displayName; }
    public LocalDateTime getJoinedAt() { return joinedAt; }
}
