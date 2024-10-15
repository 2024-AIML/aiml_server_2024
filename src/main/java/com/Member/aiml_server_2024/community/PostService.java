package com.Member.aiml_server_2024.community;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long num) {
        return postRepository.findById(num);
    }

    public Post creatPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long num, Post updatePost) {
        Optional<Post> optionalPost = postRepository.findById(num);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(updatePost.getTitle());
            existingPost.setContent(updatePost.getContent());

            return postRepository.save(existingPost);
        } else {
            throw new IllegalArgumentException("해당 번호의 게시글이 존재하지 않습니다.");
        }
    }

    public void deletePost(Long num) {
        postRepository.deleteById(num);
    }


}
