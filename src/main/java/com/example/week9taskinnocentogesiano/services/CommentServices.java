package com.example.week9taskinnocentogesiano.services;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentServices {
    Comment makeNewComment(long uid, long pid, PostDto postDto);
    Comment editComment(long cid, long uid, PostDto postDto);
    List<Comment> getAllPostComments(long pid);
    Comment deleteComment(long cid, long uid);
}
