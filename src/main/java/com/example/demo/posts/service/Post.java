package com.example.demo.posts.service;

import jakarta.validation.constraints.NotNull;

public record Post(@NotNull String publicId,
                   @NotNull String title,
                   @NotNull String body
) {
}
