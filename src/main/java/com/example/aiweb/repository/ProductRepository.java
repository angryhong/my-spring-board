package com.example.aiweb.repository;

import com.example.aiweb.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // CollectionServiceImpl 에서 호출하는 두 페이징 메서드
    Page<Product> findByCollectionId(Long collectionId, Pageable pageable);
    Page<Product> findByCollectionIdAndCategoryId(Long collectionId, Long categoryId, Pageable pageable);
}
