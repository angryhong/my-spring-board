package com.example.aiweb.api;
import com.example.aiweb.dto.PostDto;
import com.example.aiweb.service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/posts")
public class PostApiController {
    private final PostService service;
    public PostApiController(PostService service) { this.service = service; }
    @GetMapping
    public List<PostDto> list() { return service.findAll(); }
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable Long id) { return service.findById(id); }
    @PostMapping
    public PostDto create(@RequestBody PostDto dto) { return service.create(dto); }
}