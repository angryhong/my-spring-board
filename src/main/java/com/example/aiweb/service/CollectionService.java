package com.example.aiweb.service;

import com.example.aiweb.entity.Product;
import com.example.aiweb.entity.Collection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CollectionService {
    Collection findById(Long id);

    // ← 이 부분이 중요합니다!
    Page<Product> getProducts(Long collectionId,
                              Long categoryId,
                              String sort,
                              int page);

    List<Category> getAllCategories();
}
