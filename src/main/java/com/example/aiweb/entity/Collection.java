// src/main/java/com/example/aiweb/entity/Collection.java
package com.example.aiweb.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="collections")
public class Collection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // 컬렉션에 속한 상품 매핑 (필요 시)
    // @OneToMany(mappedBy="collection")
    // private List<Product> products;

    // getters & setters
}
