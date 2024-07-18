package com.example.demo.posts.controller;


import jakarta.validation.constraints.NotNull;

public record CreatePostDTO(@NotNull String title,
                            @NotNull String body
) {
}
