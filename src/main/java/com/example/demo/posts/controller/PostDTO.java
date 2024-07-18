package com.example.demo.posts.controller;

import lombok.Value;

@Value
public class PostDTO {
    String publicId;
    String title;
    String body;
}
