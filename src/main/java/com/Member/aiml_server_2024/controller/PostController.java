package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.community.Post;
import com.Member.aiml_server_2024.community.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }

    @GetMapping("/{num}")
    public Optional<Post> getPostById(@PathVariable Long num) {
        return postService.getPostById(num);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postService.creatPost(post);
    }

    @PutMapping("/{num}")
    public Post updatePost(@PathVariable Long num, @RequestBody Post updatePost) {
        return postService.updatePost(num, updatePost);
    }

    @DeleteMapping("/{num}")
    public void deletePost(@PathVariable Long num) {
        postService.deletePost(num);
    }
}
