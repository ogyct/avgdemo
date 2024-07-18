package com.example.demo.posts.controller;

import com.example.demo.common.PageObject;
import com.example.demo.posts.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.PageConstants.DEFAULT_PAGE_NUMBER;
import static com.example.demo.common.PageConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping(value = "/posts", produces = "application/json")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public PageObject<PostDTO> getPosts(
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(defaultValue = "title", required = false) String sortBy
    ) {
        var all = postService.findAll(pageNumber, pageSize, sortBy);
        return postMapper.map(all);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody CreatePostDTO createPostDTO) {
        return postMapper.map(postService.createPost(postMapper.map(createPostDTO)));
    }

    @DeleteMapping("/{publicId}")
    @Transactional
    public void deletePost(@PathVariable String publicId) {
        postService.deleteByPublicId(publicId);
    }
}
