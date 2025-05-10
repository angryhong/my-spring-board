package com.example.aiweb.controller;
import com.example.aiweb.dto.ReplyDto;
import com.example.aiweb.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService service;
    public ReplyController(ReplyService service) { this.service = service; }
    @PostMapping
    public String create(@ModelAttribute ReplyDto dto) {
        service.create(dto);
        return "redirect:/posts/" + dto.getPostId();
    }
}