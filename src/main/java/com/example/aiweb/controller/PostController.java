package com.example.aiweb.controller;

import com.example.aiweb.entity.Post;
import com.example.aiweb.service.PostService;
import com.example.aiweb.entity.Member;
import com.example.aiweb.entity.Reply;
import com.example.aiweb.service.ReplyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.List;



@Controller
public class PostController {

    @Value("${file.upload-dir}")
    private String uploadPath;
    private final PostService postService;
    private final ReplyService replyService;

    public PostController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    // 글쓰기 폼
    @GetMapping("/posts/new")
    public String showPostForm(HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }
        return "post_form";  // templates/post_form.html
    }

    // 글 목록
    /*
    @GetMapping("/posts")
    public String listPosts(Model model) {

        model.addAttribute("postList", postService.findAll());
        return "post_list";
    }


    @GetMapping("/posts")
    public String listPosts(@RequestParam(required = false) String category, Model model) {
        List<Post> posts;

        if (category == null || category.isEmpty() || category.equals("전체")) {
            posts = postService.findAll();
        } else {
            posts = postService.findByCategory(category);
        }

        model.addAttribute("postList", posts);
        return "post_list";
    }
    */
    @GetMapping("/posts")
    public String listPosts(@RequestParam(required = false) String category,
                            @RequestParam(required = false) String sort,
                            Model model) {
        List<Post> posts;

        if ("views".equals(sort)) {
            posts = postService.sortByViewCount();
        } else if ("latest".equals(sort)) {
            posts = postService.sortByLatest();
        } else if (category != null && !category.equals("전체")) {
            posts = postService.findByCategory(category);
        } else {
            posts = postService.findAll();
        }

        model.addAttribute("postList", posts);
        return "post_list";
    }




    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Long id, Model model, HttpSession session) {
        Post post = postService.findById(id);
        if (post == null)
            return "redirect:/posts";

        post.incrementViewCount();         // ✅ 1 증가
        postService.save(post);           // ✅ 저장해야 DB에 반영됨

        model.addAttribute("post", post);
        model.addAttribute("replyList", replyService.findByPostId(id));
        // ✅ 로그인한 사용자도 같이 넘기기
        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("loginMember", loginMember);

        return "post_detail";
    }

    // 글 수정
    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model, HttpSession session) {
        Post post = postService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (post == null || loginMember == null || !post.getAuthor().getId().equals(loginMember.getId())) {
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "post_edit";
    }

    // 글 삭제 처리
    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, HttpSession session) {
        Post post = postService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");

        // ✅ 로그 확인 (이 부분 추가!)
        System.out.println("삭제 시도: " + post.getId());
        System.out.println("작성자: " + post.getAuthor().getMembername());
        System.out.println("로그인 사용자: " + loginMember.getMembername());

        if (post != null && loginMember != null && post.getAuthor().getId().equals(loginMember.getId())) {
            postService.delete(post);  // 실제 삭제
        }

        return "redirect:/posts";
    }

    //마이페이지
    @GetMapping("/mypage")
    public String myPosts(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/login";
        }
        List<Post> myPosts = postService.findByAuthor(loginMember);
        model.addAttribute("postList", myPosts);
        return "my_page";
    }





    // 글 저장 처리
    @PostMapping("/posts")
    public String savePost(@RequestParam String title,
                           @RequestParam String content,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam String category, // ✅ 꼭 있어야 함
                           HttpSession session) throws IOException {

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/login";
        }

        Post post = new Post(title, content);
        post.setCategory(category); // ✅ 이 줄이 없으면 글에 카테고리가 저장되지 않음
        post.setAuthor(loginMember);

        if (!file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // ✅ 절대경로로 된 uploadPath를 사용
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

            File saveFile = new File(dir, filename);
            file.transferTo(saveFile); // 에러났던 위치

            post.setFilename(filename);
        }
        System.out.println("저장되는 category 값 = " + category);

        postService.save(post);
        return "redirect:/posts";
    }

    //글수정
    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String content,
                             HttpSession session) {
        Post post = postService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");

        System.out.println("수정 요청: " + id);
        System.out.println("새 제목: " + title);
        System.out.println("새 내용: " + content);

        if (post == null || !post.getAuthor().getId().equals(loginMember.getId())) {
            return "redirect:/posts"; // 권한 없음
        }

        // 🔁 제목/내용 수정
        post.setTitle(title);
        post.setContent(content);

        postService.save(post);  // 💾 수정 내용 DB 반영

        return "redirect:/posts/" + id;
    }

    //리플
    @PostMapping("/posts/{id}/reply")
    public String addReply(@PathVariable Long id,
                           @RequestParam String content,
                           HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) return "redirect:/login";

        Post post = postService.findById(id);
        if (post == null) return "redirect:/posts";

        Reply reply = new Reply();
        reply.setPost(post);
        reply.setAuthor(loginMember);
        reply.setContent(content);

        replyService.save(reply);
        return "redirect:/posts/" + id;
    }

    //검색
    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam String keyword, Model model) {
        List<Post> results = postService.searchByTitle(keyword);
        model.addAttribute("postList", results);
        return "post_list"; // 검색 결과도 기존 리스트 화면 재사용
    }


}
