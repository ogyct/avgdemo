package com.example.demo.posts.controller;

import com.example.demo.common.PageObject;
import com.example.demo.posts.service.CreatePost;
import com.example.demo.posts.service.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
interface PostMapper {
    PostDTO map(Post post);

    CreatePost map(CreatePostDTO post);

    List<PostDTO> map(List<Post> post);


    @Mapping(source = "content", target = "content", conditionExpression = "java(postPage.getContent()!=null)")
    PageObject<PostDTO> map(PageObject<Post> postPage);

}
