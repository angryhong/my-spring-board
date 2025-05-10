// src/main/java/com/example/aiweb/controller/MarketController.java
package com.example.aiweb.controller;

import com.example.aiweb.entity.Collection;
import com.example.aiweb.entity.Product;         // ← 이 줄
import com.example.aiweb.service.CollectionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MarketController {

    private final CollectionService collectionService;

    public MarketController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/collection/{id}")
    public String marketBest(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "best") String sort,
            @RequestParam(required = false) Long category,
            Model model) {

        Collection collection = collectionService.findById(id);
        Page<Product> products = collectionService.getProducts(id, category, sort, page);

        model.addAttribute("collection", collection);
        model.addAttribute("categories", collectionService.getAllCategories());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("sort", sort);
        model.addAttribute("page", products);

        return "market_best";
    }
}
