package com.example.aiweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /** 루트 페이지 */
    @GetMapping("/")
    public String root() {
        return "home";   // resources/templates/home.html
    }

    /** 메인 페이지 */
    @GetMapping("/main")
    public String main() {
        return "main";   // resources/templates/main.html
    }

    // @GetMapping("/login") 및 @GetMapping("/signup") 메서드는 모두 지워야 합니다.
}
