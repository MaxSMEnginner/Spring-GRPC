package com.davinchicoder.springgrpc.IT;

import com.davinchicoder.springgrpc.proto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostIT {

    @Autowired
    private CrudGrpc.CrudBlockingStub blockingStub;

    @Test
    void shouldCreateAndRetrievePost() {
        PostDto newPost = PostDto.newBuilder()
                .setTitle("Test Post")
                .setContent("Test Content")
                .setAuthor("Test Author")
                .setImageUrl("http://test.com/image.jpg")
                .build();

        PostDto savedPost = blockingStub.savePost(newPost);
        assertEquals(newPost.getTitle(), savedPost.getTitle());

        PostDto retrievedPost = blockingStub.getPostById(PostId.newBuilder()
                .setId(savedPost.getId())
                .build());
        assertEquals(savedPost, retrievedPost);
    }

    @Test
    void shouldListAllPosts() {
        PaginationRequest request = PaginationRequest.newBuilder()
                .setCount(10)
                .setOffset(0)
                .build();

        PostList response = blockingStub.getAllPosts(request);
        assertNotNull(response);
        assertTrue(response.getPostDtoCount() > 0);
    }

    @Test
    void shouldUpdatePost() {
        PostDto newPost = PostDto.newBuilder()
                .setTitle("Original Title")
                .setContent("Original Content")
                .setAuthor("Original Author")
                .setImageUrl("http://test.com/image.jpg")
                .build();

        PostDto savedPost = blockingStub.savePost(newPost);

        PostDto updateRequest = savedPost.toBuilder()
                .setTitle("Updated Title")
                .build();

        PostDto updatedPost = blockingStub.updatePost(updateRequest);
        assertEquals("Updated Title", updatedPost.getTitle());
        assertEquals(savedPost.getId(), updatedPost.getId());
    }

    @Test
    void shouldDeletePost() {
        PostDto newPost = PostDto.newBuilder()
                .setTitle("To Delete")
                .setContent("Delete Content")
                .setAuthor("Delete Author")
                .setImageUrl("http://test.com/delete.jpg")
                .build();

        PostDto savedPost = blockingStub.savePost(newPost);

        PostDto deletedPost = blockingStub.deletePost(PostId.newBuilder()
                .setId(savedPost.getId())
                .build());

        assertNotNull(deletedPost);
        assertEquals(savedPost.getId(), deletedPost.getId());
    }
}

