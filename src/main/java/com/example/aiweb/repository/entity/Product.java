package com.example.aiweb.repository.entity;
import jakarta.persistence.*;
@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String imageUrl;
    // getters/setters
}