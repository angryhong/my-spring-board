package com.example.aiweb.controller;

import com.example.aiweb.entity.Collection;
import com.example.aiweb.entity.Category;
import com.example.aiweb.entity.Product;
import com.example.aiweb.service.CollectionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/collection")
public class MarketController {

    private final CollectionService collectionService;

    public MarketController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public String marketBest(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "best") String sort,
            @RequestParam(required = false) Long category,
            Model model) {

        // 1) 컬렉션 정보
        Collection collection = collectionService.findById(id);
        // 2) 상품 페이지
        Page<Product> products = collectionService.getProducts(id, category, sort, page);
        List<Category> categories = collectionService.getAllCategories();

        model.addAttribute("collection", collection);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("sort", sort);
        model.addAttribute("page", products);

        return "market_best";
    }
}
