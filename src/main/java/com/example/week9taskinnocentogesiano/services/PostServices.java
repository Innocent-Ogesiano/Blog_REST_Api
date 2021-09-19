package com.example.week9taskinnocentogesiano.services;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PostServices {
    Post makeNewPost(long uid, PostDto postDto);
    Post editPost(long pid, long uid, PostDto postDto);
    List<Post> getAllPosts();
    Post deletePost(long pid, long uid);
    List<Post> getAllUserConnectionPost(long uid);
    ResponseEntity<Set<Post>> addPostToUserFavourite(long uid, long pid);
}
