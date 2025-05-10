package com.example.aiweb.controller;

import com.example.aiweb.repository.entity.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.aiweb.repository.entity.Role;




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
