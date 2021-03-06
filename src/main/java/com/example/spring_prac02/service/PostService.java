package com.example.spring_prac02.service;

import com.example.spring_prac02.model.Post;
import com.example.spring_prac02.dto.PostRequestDto;
import com.example.spring_prac02.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto requestDto, Long userId) {
        Post post = new Post(requestDto, userId);
        return postRepository.save(post);
    }


    public List<Post> getPosts() {
        List<Post> result = postRepository.findAllByOrderByModifiedAtDesc();
        return result;
    }

    // 상세페이지
    public Post showDetail(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
    }
}
