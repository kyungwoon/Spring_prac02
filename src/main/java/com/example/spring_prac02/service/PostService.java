package com.example.spring_prac02.service;

import com.example.spring_prac02.dto.PostRequestDto;
import com.example.spring_prac02.model.Post;
import com.example.spring_prac02.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private PostRepository postRepository;
    @Transactional
    public Post createPost(PostRequestDto requestDto, Long userId){
        Post post = new Post(requestDto,userId);
        return postRepository.save(post);
    }

    @Transactional
    public Long update(PostRequestDto requestDto, Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("ID값이 없습니다.")
        );

        post.update(requestDto);
        return post.getId();

    }
    //게시물 데이터
    public List<Post> getPosts(){
        List<Post> result = postRepository.findAllByOrderByModifiedAtDesc();
        return result;
    }
}
