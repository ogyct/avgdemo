package com.example.demo.posts.service;


import jakarta.validation.constraints.NotBlank;


public record CreatePost(@NotBlank
                         String title,
                         @NotBlank
                         String body
) {
}
