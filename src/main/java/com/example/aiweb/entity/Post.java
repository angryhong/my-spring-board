package com.example.aiweb.entity;

import jakarta.persistence.*;

@Entity
public class Post {

    @ManyToOne
    private Member author;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String category;

    @Column(nullable = false)
    private int viewCount = 0;


    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter & Setter (생략 가능)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Member getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public int getViewCount() {
        return viewCount;
    }


    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setAuthor(Member author) {
        this.author = author;
    }
    public void incrementViewCount() {
        this.viewCount++;
    }


}
