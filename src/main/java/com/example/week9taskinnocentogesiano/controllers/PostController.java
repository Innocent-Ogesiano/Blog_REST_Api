package com.example.week9taskinnocentogesiano.controllers;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Post;
import com.example.week9taskinnocentogesiano.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServices postServices;

    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping("/all")
    public List<Post> getAllPost(){
        return postServices.getAllPosts();
    }

    @PostMapping("/addNewPost")
    public Post addNewPost(@Valid @RequestParam long uid, @RequestBody PostDto postDto) {
        return postServices.makeNewPost(uid, postDto);
    }

    @PutMapping("/editPost/{pid}")
    public Post editPost(@Valid @RequestParam long uid, @PathVariable long pid, @RequestBody PostDto postDto) {
        return postServices.editPost(pid, uid, postDto);
    }

    @DeleteMapping("/deletePost/{pid}")
    public Post deletePost(@RequestParam long uid, @PathVariable long pid) {
        return postServices.deletePost(pid, uid);
    }

    @GetMapping("/viewUserConnectionPost/{uid}")
    public ResponseEntity<List<Post>> viewAllUserConnectionsPosts(@PathVariable long uid) {
        return new ResponseEntity<>(postServices.getAllUserConnectionPost(uid), HttpStatus.ACCEPTED);
    }

    @PutMapping("/addPostToFavourites/{uid}")
    public ResponseEntity<Set<Post>> addPostToUserFavourite(@PathVariable long uid, @RequestParam long pid) {
        return postServices.addPostToUserFavourite(uid, pid);
    }
}
