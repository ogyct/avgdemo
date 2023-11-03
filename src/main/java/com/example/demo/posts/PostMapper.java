package com.example.demo.posts;

import com.example.demo.common.PageDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO map(PostEntity postEntity);

    List<PostDTO> map(List<PostEntity> postEntity);

    PageDTO<PostDTO> map(Page<PostEntity> postEntityPage);

}
