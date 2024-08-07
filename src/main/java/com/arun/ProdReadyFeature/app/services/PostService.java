package com.arun.ProdReadyFeature.app.services;

import com.arun.ProdReadyFeature.app.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto postDto, Long userId);

    PostDto updatePost(PostDto postDto, Long userId,Long postId);

    Boolean deletePost(Long postId, Long userId);

    List<PostDto> getAllPostsByUserId(Long userId);


}
