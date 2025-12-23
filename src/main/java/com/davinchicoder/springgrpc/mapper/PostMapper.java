package com.davinchicoder.springgrpc.mapper;


import com.davinchicoder.springgrpc.domain.Post;
import com.davinchicoder.springgrpc.proto.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post map(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .author(postDto.getAuthor())
                .content(postDto.getContent())
                .imageUrl(postDto.getImageUrl())
                .build();
    }

    public PostDto map(Post post) {
        return PostDto.newBuilder()
                .setId(post.getId())
                .setTitle(post.getTitle())
                .setAuthor(post.getAuthor())
                .setContent(post.getContent())
                .setImageUrl(post.getImageUrl())
                .build();
    }
}
