package com.example.demo.posts.repository;

import com.example.demo.common.PageObject;
import com.example.demo.posts.service.CreatePost;
import com.example.demo.posts.service.Post;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PostEntityMapper {
    PostEntity map(CreatePost createPost);

    Post map(PostEntity postEntity);

    PageObject<Post> map(Page<PostEntity> postEntityPage);

}
