package com.example.aiweb.service;

import com.example.aiweb.entity.Category;
import com.example.aiweb.entity.Product;
import com.example.aiweb.entity.Collection;
import org.springframework.data.domain.Page;

import java.util.List;
public interface CollectionService {
    Collection findById(Long id);
    List<Category> getAllCategories();
    Page<Product> getProducts(Long collectionId, Long categoryId, String sort, int page);
}
