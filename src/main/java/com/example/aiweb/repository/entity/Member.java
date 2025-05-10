package com.example.aiweb.repository.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String displayName;
    private LocalDateTime joinedAt = LocalDateTime.now();
    // getters/setters
}