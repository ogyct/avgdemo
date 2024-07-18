package com.example.demo.posts.repository;

import com.example.demo.common.PageObject;
import com.example.demo.posts.service.CreatePost;
import com.example.demo.posts.service.Post;
import com.example.demo.posts.service.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
    private final PostEntityMapper postEntityMapper;

    @Override
    public Post save(CreatePost post) {
        return postEntityMapper.map(postJpaRepository.save(postEntityMapper.map(post)));
    }

    @Override
    public void deleteByPublicId(String publicId) {
        postJpaRepository.deleteByPublicId(publicId);
    }

    @Override
    public PageObject<Post> findAll(int page, int size, String sortBy) {
        var all = postJpaRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(sortBy)));
        return postEntityMapper.map(all);
    }
}
