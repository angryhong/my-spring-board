package com.example.aiweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    /** 로그인 폼 */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // 필요하다면 model.addAttribute(...) 로 폼 객체 바인딩
        return "login";   // resources/templates/login.html
    }

    /** 회원가입 폼 */
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        // 필요하다면 model.addAttribute(...) 로 폼 객체 바인딩
        return "signup";  // resources/templates/signup.html
    }
}
