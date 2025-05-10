// src/main/java/com/example/aiweb/service/impl/CollectionServiceImpl.java
package com.example.aiweb.service.impl;

import com.example.aiweb.entity.Collection;
import com.example.aiweb.repository.CollectionRepository;
import com.example.aiweb.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepo;
    // private final ProductRepository productRepo;
    // private final CategoryRepository categoryRepo;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepo/*, … */) {
        this.collectionRepo = collectionRepo;
    }

    @Override
    public Collection findById(Long id) {
        return collectionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("컬렉션이 존재하지 않습니다: "+ id));
    }

    @Override
    public Page<Product> getProducts(Long collectionId,
                                     Long categoryId,
                                     String sort,
                                     int page) {
        // 예시: Spring Data Pageable 사용
        Sort s = switch (sort) {
            case "new"  -> Sort.by("createdAt").descending();
            case "low"  -> Sort.by("price").ascending();
            case "high" -> Sort.by("price").descending();
            default     -> Sort.by("soldCount").descending(); // 인기순
        };
        Pageable pageable = PageRequest.of(page - 1, 20, s);

        // 간단히 전체 Product 페이징 반환 예시
        return productRepo.findByCollectionIdAndCategoryId(collectionId, categoryId, pageable);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

}
