package com.example.spring_prac02.controller;

import com.example.spring_prac02.model.Post;
import com.example.spring_prac02.security.UserDetailsImpl;
import com.example.spring_prac02.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            if (userDetails != null) {
                model.addAttribute("name", userDetails.getUser().getName()); //사용자 이름
                model.addAttribute("userId", userDetails.getUser().getId()); //USER테이블 고유번호
            }

            List<Post> postList = postService.getPosts();
            model.addAttribute("postList", postList);

        } catch (Exception e) {

        }

        return "index";
    }
}