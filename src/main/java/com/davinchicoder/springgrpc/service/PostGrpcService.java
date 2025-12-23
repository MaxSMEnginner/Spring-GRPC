package com.davinchicoder.springgrpc.service;

import com.davinchicoder.springgrpc.domain.Post;
import com.davinchicoder.springgrpc.exception.PostNotFoundException;
import com.davinchicoder.springgrpc.mapper.PostMapper;
import com.davinchicoder.springgrpc.proto.*;
import com.davinchicoder.springgrpc.repository.PostRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class PostGrpcService extends CrudGrpc.CrudImplBase {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void getAllPosts(PaginationRequest request, StreamObserver<PostList> responseObserver) {
        List<PostDto> list = postRepository.getAll(request.getCount(), request.getOffset())
                .stream()
                .map(postMapper::map)
                .toList();
        PostList reply = PostList.newBuilder().addAllPostDto(list).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getPostById(PostId request, StreamObserver<PostDto> responseObserver) {

        Post post = postRepository.getById(request.getId()).orElseThrow(PostNotFoundException::new);

        PostDto reply = postMapper.map(post);

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void savePost(PostDto request, StreamObserver<PostDto> responseObserver) {

        Post post = postMapper.map(request);

        Post saved = postRepository.save(post);

        PostDto reply = postMapper.map(saved);

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePost(PostId request, StreamObserver<PostDto> responseObserver) {

        Optional<Post> deleted = postRepository.delete(request.getId());

        PostDto reply = deleted.map(postMapper::map).orElseThrow(PostNotFoundException::new);

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePost(PostDto request, StreamObserver<PostDto> responseObserver) {

        Post post = postMapper.map(request);

        Post updated = postRepository.update(post);

        PostDto reply = postMapper.map(updated);

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
