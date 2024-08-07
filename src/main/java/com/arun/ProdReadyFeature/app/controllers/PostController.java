package com.arun.ProdReadyFeature.app.controllers;

import com.arun.ProdReadyFeature.app.dto.PostDto;
import com.arun.ProdReadyFeature.app.services.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    //Create Post for User

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto,@PathVariable Long userId){
        return postService.createNewPost(postDto, userId);
    }

    //Update Post for a User

    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable Long userId, @PathVariable Long postId,@RequestBody PostDto postDto){
        return postService.updatePost(postDto,userId,postId);
    }

    //Delete Post for a User

    @DeleteMapping("/{postId}")
    public Boolean deletePost(@PathVariable Long userId,@PathVariable Long postId){
        return postService.deletePost(userId,postId);
    }

    //Get All Posts for User

    @GetMapping
    public List<PostDto> getAllPostsByUser(@PathVariable Long userId){
        return postService.getAllPostsByUserId(userId);
    }



}
