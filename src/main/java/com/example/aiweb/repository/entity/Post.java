package com.example.aiweb.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;
    private LocalDateTime createdAt = LocalDateTime.now();

    // setter / getter 생략 (IDE 자동 생성)
}
