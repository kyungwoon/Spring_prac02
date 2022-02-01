package com.example.spring_prac02.model;

import com.example.spring_prac02.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    // 댓글 번호
    private Long Id;

    // 게시물 번호
    @Column(nullable = false)
    private Long postId;

    // 사용자 번호
    @Column(nullable = false)
    private Long userId;

    //사용자 이름
    @Column(nullable = false)
    private String name;

    // 댓글 내용
    private String comment;

    public Comment(CommentRequestDto requestDto){
        this.postId = requestDto.getPostId();
        this.userId = requestDto.getUserId();
        this. name  = requestDto.getName();
        this.comment = requestDto.getComment();
    }

    public void update(CommentRequestDto requestDto){
        this.postId = requestDto.getPostId();
        this.userId = requestDto.getUserId();
        this. name  = requestDto.getName();
        this.comment = requestDto.getComment();
    }
}
