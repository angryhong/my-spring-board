package com.example.aiweb.controller;
import com.example.aiweb.dto.MemberDto;
import com.example.aiweb.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;
    public MemberController(MemberService service) { this.service = service; }
    @GetMapping("/new") public String form() { return "signup_form"; }
    @PostMapping
    public String register(@ModelAttribute MemberDto dto) {
        service.register(dto);
        return "redirect:/login";
    }
}