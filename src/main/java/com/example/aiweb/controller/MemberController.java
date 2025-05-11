package com.example.aiweb.controller;

import com.example.aiweb.dto.MemberDto;
import com.example.aiweb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 1) 로그인 폼 보여주기
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // 에러 메시지(FlashAttr)가 있으면 뷰에서 꺼내 쓸 수 있도록
        return "login";  // templates/login.html 렌더링
    }

    // 2) 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes attrs
    ) {
        try {
            MemberDto dto = memberService.login(username, password);  // 구현해 두신 login 메서드 호출
            session.setAttribute("loginMember", dto);
            return "redirect:/";  // 로그인 성공하면 홈으로
        } catch (IllegalArgumentException e) {
            attrs.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/login";
        }
    }

    // 3) 회원가입 폼 보여주기
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";  // templates/signup.html
    }

    // 4) 회원가입 처리
    @PostMapping("/signup")
    public String signup(
            @ModelAttribute MemberDto dto,
            RedirectAttributes attrs
    ) {
        memberService.register(dto);
        attrs.addFlashAttribute("successMessage", "회원가입이 완료되었습니다. 로그인 해주세요.");
        return "redirect:/login";
    }
}
