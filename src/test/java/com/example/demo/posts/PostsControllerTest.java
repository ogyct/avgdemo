package com.example.demo.posts;

import com.example.demo.IntegrationTest;
import com.example.demo.posts.controller.CreatePostDTO;
import com.example.demo.posts.repository.PostJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class PostsControllerTest extends IntegrationTest {
    @Autowired
    private PostJpaRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPageOfPosts() throws Exception {
        mockMvc.perform(get("/posts?pageSize=2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("title100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].body").value("body100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].publicId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].title").value("title1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].body").value("body1000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].publicId").isNotEmpty())
        ;
    }

    @Test
    void shouldDeletePost() throws Exception {
        var post1 = postRepository.findById(1L).orElseThrow();
        mockMvc.perform(delete("/posts/" + post1.getPublicId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
        assertFalse(postRepository.findById(1L).isPresent());
    }

    @Test
    void createPost_ok() throws Exception {
        var newPost = new CreatePostDTO("post title", "post body");
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPost)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("post title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("post body"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicId").exists())
        ;
    }

    @Test
    void createPost_shouldFail_ifNoTitle() throws Exception {
        var newPost = new CreatePostDTO(null, "post body");
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPost)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$['createPost.createPost.title']").value("must not be blank"))
        ;
    }

}
