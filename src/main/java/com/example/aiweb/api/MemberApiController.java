package com.example.aiweb.api;
import com.example.aiweb.dto.MemberDto;
import com.example.aiweb.service.MemberService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/members")
public class MemberApiController {
    private final MemberService service;
    public MemberApiController(MemberService service) { this.service = service; }
    @GetMapping("/{id}")
    public MemberDto detail(@PathVariable Long id) { return service.findById(id); }
    @PostMapping
    public MemberDto register(@RequestBody MemberDto dto) { return service.register(dto); }
}