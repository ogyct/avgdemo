package com.example.demo.posts;

import com.example.demo.common.PageDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.PageConstants.DEFAULT_PAGE_NUMBER;
import static com.example.demo.common.PageConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping(value = "/posts", produces = "application/json")
@RequiredArgsConstructor
public class PostsController {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @GetMapping
    public PageDTO<PostDTO> getPosts(
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(defaultValue = "title", required = false) String sortBy
    ) {
        return postMapper.map(
                postRepository.findAll(
                        PageRequest.of(pageNumber, pageSize).withSort(Sort.by(sortBy))
                )
        );
    }

    @DeleteMapping("/{publicId}")
    @Transactional
    public void deletePost(@PathVariable String publicId) {
        postRepository.deleteByPublicId(publicId);
    }
}
