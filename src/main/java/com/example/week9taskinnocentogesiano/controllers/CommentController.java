package com.example.week9taskinnocentogesiano.controllers;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Comment;
import com.example.week9taskinnocentogesiano.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping("/all/{pid}")
    public List<Comment> getAllPostComments(@PathVariable long pid) {
        return commentServices.getAllPostComments(pid);
    }

    @PostMapping("/makeNewComment/{pid}")
    public Comment commentOnPost(@Valid @RequestParam long uid, @RequestBody PostDto postDto, @PathVariable long pid) {
        return commentServices.makeNewComment(uid, pid, postDto);
    }

    @PutMapping("/editComment/{cid}")
    public Comment editComment(@Valid @RequestParam long uid, @PathVariable long cid, @RequestBody PostDto postDto) {
        return commentServices.editComment(cid, uid, postDto);
    }

    @DeleteMapping("/deleteComment/{cid}")
    public Comment deleteComment(@RequestParam long uid, @PathVariable long cid) {
        return commentServices.deleteComment(cid, uid);
    }
}
