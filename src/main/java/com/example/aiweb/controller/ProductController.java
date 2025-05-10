package com.example.aiweb.controller;
import com.example.aiweb.dto.ProductDto;
import com.example.aiweb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }
    @GetMapping
    public String list(Model m) {
        m.addAttribute("products", service.findAll());
        return "product_list";
    }
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model m) {
        m.addAttribute("product", service.findById(id));
        return "product_detail";
    }
}