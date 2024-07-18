package com.example.demo.posts.service;

import com.example.demo.common.PageObject;

public interface PostRepository {
    Post save(CreatePost post);

    void deleteByPublicId(String publicId);

    PageObject<Post> findAll(int page, int size, String sortBy);
}
