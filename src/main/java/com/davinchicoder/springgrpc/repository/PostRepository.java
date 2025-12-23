package com.davinchicoder.springgrpc.repository;

import com.davinchicoder.springgrpc.domain.Post;
import com.davinchicoder.springgrpc.exception.PostNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private final List<Post> POSTS = new ArrayList<>(
            List.of(
                    Post.builder().id(1L)
                            .title("First Post")
                            .content("This is the content of the first post")
                            .author("John Doe")
                            .imageUrl("https://example.com/image1.jpg")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build(),
                    Post.builder()
                            .id(2L)
                            .title("Second Post")
                            .content("Content for the second post")
                            .author("Jane Smith")
                            .imageUrl("https://example.com/image2.jpg")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build(),
                    Post.builder()
                            .id(3L)
                            .title("Third Post")
                            .content("Content for the third post")
                            .author("Bob Wilson")
                            .imageUrl("https://example.com/image3.jpg")
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build()
            )
    );


    public List<Post> getAll(int count, int offset) {
        return POSTS.stream()
                .filter(post -> post.getDeletedAt() == null)
                .toList()
                .subList(offset, Math.min(offset + count, POSTS.size()));
    }

    public Post save(Post post) {
        post.setId(this.getNextId());
        post.setCreatedAt(LocalDateTime.now());

        POSTS.add(post);
        return post;
    }

    public Optional<Post> delete(Long id) {
        Optional<Post> postToDelete = POSTS.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        postToDelete.ifPresent(post -> post.setDeletedAt(LocalDateTime.now()));

        return postToDelete;
    }

    public Optional<Post> getById(Long id) {
        return POSTS.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public Post update(Post post) {

        Post updated = this.getById(post.getId()).orElseThrow(PostNotFoundException::new);

        updated.setTitle(post.getTitle());
        updated.setContent(post.getContent());
        updated.setAuthor(post.getAuthor());
        updated.setImageUrl(post.getImageUrl());
        updated.setUpdatedAt(LocalDateTime.now());

        return updated;
    }

    private Long getNextId() {
        return POSTS.stream().mapToLong(Post::getId).max().orElse(0L) + 1L;
    }

}
