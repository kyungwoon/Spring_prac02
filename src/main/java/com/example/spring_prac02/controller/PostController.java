package com.example.spring_prac02.controller;

import com.example.spring_prac02.dto.PostRequestDto;
import com.example.spring_prac02.model.Post;
import com.example.spring_prac02.security.UserDetailsImpl;
import com.example.spring_prac02.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    //글쓰기 페이지 이동
    @GetMapping("/post")
    public String writePost(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getUser().getId());
            model.addAttribute("name", userDetails.getUser().getName());
        }
        return "write_post";
    }

    //글쓰기(DB Insert)
    @PostMapping("/posts/post")
    public String insertPost(PostRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //로그인 되어 있는 회원 테이블의 ID값
        Long userId = userDetails.getUser().getId();
        postService.createPost(requestDto, userId);
        return "redirect:/";
    }

    //상세보기 페이지 이동
    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            model.addAttribute("userId", userDetails.getUser().getId()); //로그인 되어 있는 회원 테이블의 ID값
            model.addAttribute("name", userDetails.getUser().getName()); //사용자 이름
        } catch (Exception e) {
        }
        Post post = postService.showDetail(id);
        model.addAttribute("post", post); //게시물 데이터
        return "post_detail";
    }

}
