package com.example.aiweb.repository.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "replies")
public class Reply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private String commenter;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime commentedAt = LocalDateTime.now();
    // getters/setters
}