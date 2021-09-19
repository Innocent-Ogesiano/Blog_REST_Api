package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Post;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.*;
import com.example.week9taskinnocentogesiano.services.PostServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PostServiceImpl implements PostServices {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post makeNewPost(long uid, PostDto postDto) {
        User user = userRepository.getById(uid);
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        Post post = new Post();
        post.setPostContent(postDto.getPostContent());
        post.setDatePosted(date);
        post.setTimePosted(time);
        post.setUser(user);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post editPost(long pid, long uid, PostDto postDto) {
        Post post = postRepository.getById(pid);
        if (post.getUser().getUserId() == uid) {
            post.setPostContent(postDto.getPostContent());
            postRepository.save(post);
        } else {
            log.info("Invalid User!");
        }
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post deletePost(long pid, long uid) {
        Post post = postRepository.getById(pid);
        if (post.getUser().getUserId() == uid) {
            postRepository.delete(post);
        } else {
            log.info("Invalid User");
        }
        return post;
    }

    @Override
    public List<Post> getAllUserConnectionPost(long uid) {
        User user = userRepository.getById(uid);
        List<Post> postList = new ArrayList<>();
        if (user.getConnections() != null) {
            for (User user1 : user.getConnections().getListOfConnections()) {
                postList.addAll(postRepository.findAllByUser(user1));
            }
        }
        return postList;
    }

    @Override
    public ResponseEntity<Set<Post>> addPostToUserFavourite(long uid, long pid) {
        User user = userRepository.getById(uid);
        Post post = postRepository.getById(pid);
        Set<Post> postList;
        if (user.getListOfFavouritePosts() == null) {
            postList = new HashSet<>();
            postList.add(post);
        } else {
            postList = user.getListOfFavouritePosts();
            if (postList.contains(post)) {
                postList.remove(post);
            } else {
                postList.add(post);
            }
        }
        user.setListOfFavouritePosts(postList);
        userRepository.save(user);
        return new ResponseEntity<>(postList, HttpStatus.CREATED);
    }
}
