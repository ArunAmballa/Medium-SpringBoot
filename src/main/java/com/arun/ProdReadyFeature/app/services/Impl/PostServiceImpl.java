package com.arun.ProdReadyFeature.app.services.Impl;

import com.arun.ProdReadyFeature.app.dto.PostDto;
import com.arun.ProdReadyFeature.app.entities.Post;
import com.arun.ProdReadyFeature.app.entities.User;
import com.arun.ProdReadyFeature.app.exceptions.ResourceNotFoundException;
import com.arun.ProdReadyFeature.app.repositories.PostRepository;
import com.arun.ProdReadyFeature.app.repositories.UserRepository;
import com.arun.ProdReadyFeature.app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,UserRepository userRepository){
        this.modelMapper=modelMapper;
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }

    //User Specific Operations on Post

    @Override
    public PostDto createNewPost(PostDto postDto,Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        Post post=modelMapper.map(postDto,Post.class);
        post.setUser(user);
        post=postRepository.save(post);
        return modelMapper.map(post,PostDto.class);

    }


    public PostDto updatePost(PostDto postDto,Long userId,Long postId){
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not Found"));
        post.setUser(user);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);

    }


    public Boolean deletePost(Long postId,Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not Found"));
        postRepository.delete(post);
        return true;
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId).stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }


    public List<PostDto> getAllPosts(){
        return postRepository.findAll().stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

}
