// src/main/java/com/example/aiweb/service/impl/ProductServiceImpl.java
package com.example.aiweb.service.impl;

import com.example.aiweb.entity.Product;
import com.example.aiweb.repository.ProductRepository;
import com.example.aiweb.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 없습니다. id=" + id));
    }
}
