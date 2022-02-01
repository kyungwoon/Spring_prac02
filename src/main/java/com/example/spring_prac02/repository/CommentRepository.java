package com.example.spring_prac02.repository;

import com.example.spring_prac02.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByModifiedAtDesc(Long postId);
}
