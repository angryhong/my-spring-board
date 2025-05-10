package com.example.aiweb.dto;

// View/Controller ↔ Service 사이에서 사용할 단순 객체
public class ProductDto {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    // 생성자(필수 필드만)
    public ProductDto(Long id, String name, int price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // getter (setter는 필요하다면 추가)
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}
