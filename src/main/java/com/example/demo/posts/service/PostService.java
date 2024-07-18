package com.example.demo.posts.service;

import com.example.demo.common.PageObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class PostService {

    private final PostRepository postRepository;


    public PageObject<Post> findAll(int pageNumber, int pageSize, String sortBy) {
        return postRepository.findAll(pageNumber, pageSize, sortBy);
    }

    public void deleteByPublicId(String publicId) {
        postRepository.deleteByPublicId(publicId);
    }

    public Post createPost(@Valid CreatePost createPost) {
        return postRepository.save(createPost);
    }
}
