package com.example.aiweb.controller;

import com.example.aiweb.entity.Member;
import com.example.aiweb.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import jakarta.servlet.http.HttpSession;



@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam String membername,
                         @RequestParam String password,
                         @RequestParam String email) {
        Member member = new Member(membername, password, email);
        memberService.saveMember(member);
        return "redirect:/";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Member> memberList = memberService.getAllMembers();
        model.addAttribute("membername", memberList);
        return "members";
    }
    //로그인
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // templates/login.html 로 이동
    }

    @PostMapping("/login")
    public String login(@RequestParam String membername,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Member member = memberService.findByMembername(membername);

        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("loginMember", member);
            return "redirect:/";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login";
        }
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 초기화 (로그아웃)
        return "redirect:/login";
    }
    //비밀번호 변경
    @GetMapping("/members/change-password")
    public String showChangePasswordForm(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) return "redirect:/login";
        model.addAttribute("member", loginMember);
        return "change_password";
    }

    @PostMapping("/members/change-password")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) return "redirect:/login";

        if (!loginMember.getPassword().equals(oldPassword)) {
            model.addAttribute("error", "기존 비밀번호가 일치하지 않습니다.");
            return "change_password";
        }

        loginMember.setPassword(newPassword);
        memberService.save(loginMember); // 기존 save 메서드 사용
        return "redirect:/";
    }








}
