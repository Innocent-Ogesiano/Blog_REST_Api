package com.example.week9taskinnocentogesiano.services.serviceImpl;

import com.example.week9taskinnocentogesiano.dto.PostDto;
import com.example.week9taskinnocentogesiano.model.Comment;
import com.example.week9taskinnocentogesiano.model.Post;
import com.example.week9taskinnocentogesiano.model.User;
import com.example.week9taskinnocentogesiano.repositories.CommentRepository;
import com.example.week9taskinnocentogesiano.repositories.PostRepository;
import com.example.week9taskinnocentogesiano.repositories.UserRepository;
import com.example.week9taskinnocentogesiano.services.CommentServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Slf4j
@Service
public class CommentServiceImpl implements CommentServices {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository,
                              CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment makeNewComment(long uid, long pid, PostDto postDto) {
        Comment comment = new Comment();
        User user = userRepository.getById(uid);
        Post post = postRepository.getById(pid);
        comment.setCommentContent(postDto.getPostContent());
        comment.setDateCommented(Date.valueOf(LocalDate.now()));
        comment.setTimeCommented(Time.valueOf(LocalTime.now()));
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
        post.setNoOfComments(getAllPostComments(post.getPostId()).size());
        postRepository.save(post);
        return comment;
    }

    @Override
    public Comment editComment(long cid, long uid, PostDto postDto) {
        Comment comment = commentRepository.getById(cid);
        if (comment.getUser().getUserId() == uid) {
            comment.setCommentContent(postDto.getPostContent());
            commentRepository.save(comment);
        } else {
            log.info("Invalid operation");
        }
        return comment;
    }

    @Override
    public List<Comment> getAllPostComments(long pid) {
        return commentRepository.findCommentsByPost_PostId(pid);
    }

    public Comment deleteComment(long cid, long uid) {
        Comment comment = commentRepository.getById(cid);
        if (comment.getUser().getUserId() == uid) {
            comment.getPost().setNoOfComments(comment.getPost().getNoOfComments() - 1);
            postRepository.save(comment.getPost());
            commentRepository.delete(comment);
        } else {
            log.info("Invalid operation");
        }
        return comment;
    }
}
