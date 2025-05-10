package com.example.aiweb.service.impl;
import com.example.aiweb.dto.ProductDto;
import com.example.aiweb.repository.entity.Product;
import com.example.aiweb.repository.ProductRepository;
import com.example.aiweb.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    public ProductServiceImpl(ProductRepository repo) { this.repo = repo; }
    @Override public List<ProductDto> findAll() {
        return repo.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()))
                .collect(Collectors.toList()); }
    @Override public ProductDto findById(Long id) {
        Product p = repo.findById(id).orElseThrow(() -> new RuntimeException("상품이 없습니다: " + id));
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()); }
}