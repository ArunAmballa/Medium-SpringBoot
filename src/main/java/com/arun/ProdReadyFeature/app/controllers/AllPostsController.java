package com.arun.ProdReadyFeature.app.controllers;

import com.arun.ProdReadyFeature.app.dto.PostDto;
import com.arun.ProdReadyFeature.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class AllPostsController {

    private final PostService postService;

    @Autowired
    public AllPostsController(PostService postService) {
        this.postService = postService;
    }

    //Get All Posts

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }


}
