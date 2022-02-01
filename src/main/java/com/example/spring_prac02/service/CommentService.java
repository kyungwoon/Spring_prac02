package com.example.spring_prac02.service;

import com.example.spring_prac02.dto.CommentRequestDto;
import com.example.spring_prac02.model.Comment;
import com.example.spring_prac02.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long postid) {
        return commentRepository.findAllByPostIdOrderByModifiedAtDesc(postid);
    }

    @Transactional
    public Comment createComment(CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }

    @Transactional
    public Long updateComment(Long id, CommentRequestDto requestDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException(("저장된 ID가 없습니다."))
        );
        comment.update(requestDto);
        return comment.getId();
    }

    @Transactional
    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }

}
