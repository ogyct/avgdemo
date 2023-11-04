package com.example.demo.posts;

import com.example.demo.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class PostsControllerTest extends IntegrationTest {
    @Autowired
    private PostRepository postRepository;

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

}
