package com.example.aiweb.api;
import com.example.aiweb.dto.ProductDto;
import com.example.aiweb.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductApiController {
    private final ProductService service;
    public ProductApiController(ProductService service) { this.service = service; }
    @GetMapping
    public List<ProductDto> list() { return service.findAll(); }
    @GetMapping("/{id}")
    public ProductDto detail(@PathVariable Long id) { return service.findById(id); }
}