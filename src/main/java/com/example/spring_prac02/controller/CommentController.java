package com.example.spring_prac02.controller;

import com.example.spring_prac02.dto.CommentRequestDto;
import com.example.spring_prac02.model.Comment;
import com.example.spring_prac02.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 게시물의 모든 댓글
    @GetMapping("/comments/comment/{id}")
    public List<Comment> getComments(@PathVariable("id") Long postId) {
        return commentService.getComments(postId);
    }

    // 게시물 댓글 작성
    @PostMapping("/comments/comment")
    public Comment writeComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    // 게시물 댓글 수정
    @PutMapping("/comments/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    @DeleteMapping("/comments/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return id;
    }
}
