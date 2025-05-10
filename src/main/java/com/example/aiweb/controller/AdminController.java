package com.example.aiweb.controller;

import com.example.aiweb.entity.Member;
import com.example.aiweb.entity.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null || loginMember.getRole() != Role.ADMIN) {
            return "redirect:/";
        }
        model.addAttribute("loginMember", loginMember);
        return "admin_page";
    }
}