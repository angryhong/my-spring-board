// src/main/java/com/example/aiweb/service/ProductService.java
package com.example.aiweb.service;

import com.example.aiweb.entity.Product;

public interface ProductService {
    Product findById(Long id);
}
