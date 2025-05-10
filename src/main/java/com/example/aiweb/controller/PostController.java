package com.example.aiweb.controller;
import com.example.aiweb.dto.PostDto;
import com.example.aiweb.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService service;
    public PostController(PostService service) { this.service = service; }
    @GetMapping
    public String list(Model m) {
        m.addAttribute("posts", service.findAll());
        return "post_list";
    }
    @GetMapping("/new")
    public String form() { return "post_form"; }
    @PostMapping
    public String create(@ModelAttribute PostDto dto) {
        service.create(dto);
        return "redirect:/posts";
    }
}