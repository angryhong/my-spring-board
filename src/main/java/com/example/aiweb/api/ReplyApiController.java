package com.example.aiweb.api;
import com.example.aiweb.dto.ReplyDto;
import com.example.aiweb.service.ReplyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/replies")
public class ReplyApiController {
    private final ReplyService service;
    public ReplyApiController(ReplyService service) { this.service = service; }
    @GetMapping("/post/{postId}")
    public List<ReplyDto> list(@PathVariable Long postId) { return service.findByPostId(postId); }
    @PostMapping
    public ReplyDto create(@RequestBody ReplyDto dto) { return service.create(dto); }
}