package com.example.aiweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 루트로 들어오면 로그인 페이지로
    @GetMapping("/")
    public String root() {
                return "redirect:/login";
            }

    // 회원가입 폼 뷰
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // (필요하다면) 메인 페이지
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
