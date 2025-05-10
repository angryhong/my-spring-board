// src/main/java/com/example/aiweb/repository/ProductRepository.java
package com.example.aiweb.repository;

import com.example.aiweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
