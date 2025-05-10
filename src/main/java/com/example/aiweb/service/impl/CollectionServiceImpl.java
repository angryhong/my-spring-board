package com.example.aiweb.service.impl;

import com.example.aiweb.entity.Category;
import com.example.aiweb.entity.Collection;
import com.example.aiweb.entity.Product;
import com.example.aiweb.exception.ResourceNotFoundException;
import com.example.aiweb.repository.CategoryRepository;
import com.example.aiweb.repository.CollectionRepository;
import com.example.aiweb.repository.ProductRepository;
import com.example.aiweb.service.CollectionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepo;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public CollectionServiceImpl(CollectionRepository collectionRepo,
                                 ProductRepository productRepo,
                                 CategoryRepository categoryRepo) {
        this.collectionRepo = collectionRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Collection findById(Long id) {
        return collectionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("컬렉션이 없습니다: " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<Product> getProducts(Long collectionId,
                                     Long categoryId,
                                     String sort,
                                     int page) {
        Sort s;
        switch (sort) {
            case "new":  s = Sort.by("createdAt").descending(); break;
            case "low":  s = Sort.by("price").ascending();      break;
            case "high": s = Sort.by("price").descending();     break;
            default:     s = Sort.by("soldCount").descending(); break;
        }
        Pageable pageable = PageRequest.of(page - 1, 20, s);

        if (categoryId != null) {
            return productRepo.findByCollectionIdAndCategoryId(collectionId, categoryId, pageable);
        }
        return productRepo.findByCollectionId(collectionId, pageable);
    }
}
