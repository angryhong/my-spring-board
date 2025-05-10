package com.example.aiweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer discountedPrice;
    private String imageUrl;

    // 생성일 기준 정렬용
    private java.time.LocalDateTime createdAt;

    // 인기순 정렬용(예: 판매 수)
    private Long soldCount;

    // Category, Collection 과 연관관계가 필요하면 여기에 매핑 추가

    // --- getters / setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(Integer discountedPrice) { this.discountedPrice = discountedPrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getSoldCount() { return soldCount; }
    public void setSoldCount(Long soldCount) { this.soldCount = soldCount; }
}
