package com.example.demo.posts;

import com.example.demo.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class PostsControllerTest extends IntegrationTest {

    @Test
    void shouldReturnPageOfPosts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()").value(10))
        ;
    }

}
