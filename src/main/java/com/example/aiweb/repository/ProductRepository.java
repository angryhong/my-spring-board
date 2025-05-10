package com.example.aiweb.repository;
import com.example.aiweb.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {}