package com.example.demo.posts;

import lombok.Value;

@Value
public class PostDTO {
    String publicId;
    String title;
    String body;
}
