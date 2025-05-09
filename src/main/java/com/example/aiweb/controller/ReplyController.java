package com.example.aiweb.controller;

import com.example.aiweb.entity.Reply;
import com.example.aiweb.entity.Member;
import com.example.aiweb.service.ReplyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/reply/{id}/delete")
    public String deleteReply(@PathVariable Long id, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        Reply reply = replyService.findById(id);

        if (reply != null && loginMember != null
                && reply.getAuthor().getId().equals(loginMember.getId())) {
            Long postId = reply.getPost().getId(); // 삭제 후 돌아갈 게시글 ID
            replyService.delete(reply); // 실제 삭제
            return "redirect:/posts/" + postId;
        }

        return "redirect:/posts";
    }

    @GetMapping("/reply/{id}/edit")
    public String editReplyForm(@PathVariable Long id, Model model, HttpSession session) {
        Reply reply = replyService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (reply == null || loginMember == null || !reply.getAuthor().getId().equals(loginMember.getId())) {
            return "redirect:/posts";
        }

        model.addAttribute("reply", reply);
        return "reply_edit"; // → templates/reply_edit.html
    }

    //post
    @PostMapping("/reply/{id}/edit")
    public String updateReply(@PathVariable Long id,
                              @RequestParam String content,
                              HttpSession session) {
        Reply reply = replyService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (reply == null || loginMember == null || !reply.getAuthor().getId().equals(loginMember.getId())) {
            return "redirect:/posts";
        }

        reply.setContent(content);
        replyService.save(reply);  // 수정 저장

        return "redirect:/posts/" + reply.getPost().getId();
    }


}
