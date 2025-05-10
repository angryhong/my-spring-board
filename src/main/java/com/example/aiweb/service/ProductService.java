package com.example.aiweb.service;
import com.example.aiweb.dto.ProductDto;
import java.util.List;
public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
}